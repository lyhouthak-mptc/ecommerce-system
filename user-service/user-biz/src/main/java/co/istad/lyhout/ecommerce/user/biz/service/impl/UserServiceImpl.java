package co.istad.lyhout.ecommerce.user.biz.service.impl;

import co.istad.lyhout.ecommerce.user.biz.exception.PasswordMismatchException;
import co.istad.lyhout.ecommerce.user.biz.exception.UsernameConflictException;
import co.istad.lyhout.ecommerce.user.biz.mapper.UserMapper;
import co.istad.lyhout.ecommerce.user.biz.repository.UserRepository;
import co.istad.lyhout.ecommerce.user.biz.service.KeycloakRoleService;
import co.istad.lyhout.ecommerce.user.biz.service.KeycloakUserService;
import co.istad.lyhout.ecommerce.user.biz.service.UserService;
import co.istad.lyhout.ecommerce.user.dto.UserCreateRequest;
import co.istad.lyhout.ecommerce.user.dto.UserResponse;
import co.istad.lyhout.ecommerce.user.dto.UserUpdateRequest;
import co.istad.lyhout.ecommerce.user.biz.exception.UserNotFoundException;
import co.istad.lyhout.ecommerce.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakUserService keycloakUserService;
    private final KeycloakRoleService keycloakRoleService;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toUserEntity(userCreateRequest);

        // Validate username
        if (userRepository.existsByUsername(userCreateRequest.username())) {
            throw new UsernameConflictException(HttpStatus.CONFLICT,
                    "Username already exists");
        }

        // Validate password
        if (!userCreateRequest.password().equals(userCreateRequest.confirmedPassword())) {
            throw new PasswordMismatchException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        // Save user into Keycloak
        // After saved user, save user into DB
        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        keycloakUserService.createKeycloakUser(userRepresentation);
        UserRepresentation createdUser = keycloakUserService.getUserByUsername(
                userEntity.getUsername()
        );
        log.info("User created: {}", createdUser);
        keycloakRoleService.assignRoles(createdUser.getId(), userCreateRequest.roleName());
        // Saving user into database
        userEntity.setId(createdUser.getId());
        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity, createdUser);
    }

    @Override
    public List<UserResponse> getUsersByRoleName(String roleName) {
        return joinWithDatabase(keycloakUserService.getUsersByRoleName(roleName));
    }

    @Override
    public List<UserResponse> getUsers() {
        return joinWithDatabase(keycloakUserService.getUsers());
    }

    @Override
    public UserResponse getUserById(String id) {
        UserEntity userEntity = findUserEntity(id);
        UserRepresentation userRepresentation = keycloakUserService.getUserById(id);
        return userMapper.toResponse(userEntity, userRepresentation);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with username " + username + " not found"));
        UserRepresentation userRepresentation = keycloakUserService.getUserById(userEntity.getId());
        return userMapper.toResponse(userEntity, userRepresentation);
    }

    @Transactional
    @Override
    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = findUserEntity(id);

        // 1. Update identity fields in Keycloak (only the ones sent)
        UserRepresentation userRepresentation = keycloakUserService.getUserById(id);
        if (userUpdateRequest.email() != null) {
            userRepresentation.setEmail(userUpdateRequest.email());
            userRepresentation.setEmailVerified(false);
        }
        if (userUpdateRequest.familyName() != null) {
            userRepresentation.setFirstName(userUpdateRequest.familyName());
        }
        if (userUpdateRequest.givenName() != null) {
            userRepresentation.setLastName(userUpdateRequest.givenName());
        }
        keycloakUserService.updateUser(id, userRepresentation);

        // 2. Update profile fields in the database
        userMapper.updateUserEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity, userRepresentation);
    }

    @Transactional
    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = findUserEntity(id);
        // Delete in Keycloak first; if it fails, the DB row is kept
        keycloakUserService.deleteUser(id);
        userRepository.delete(userEntity);
    }

    private UserEntity findUserEntity(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "User with id " + id + " not found"));
    }

    // Merge Keycloak users with their DB profile in one query (skips users not in the DB)
    private List<UserResponse> joinWithDatabase(List<UserRepresentation> keycloakUsers) {
        Map<String, UserEntity> entitiesById = userRepository
                .findAllById(keycloakUsers.stream().map(UserRepresentation::getId).toList())
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, Function.identity()));

        return keycloakUsers.stream()
                .filter(user -> entitiesById.containsKey(user.getId()))
                .map(user -> userMapper.toResponse(entitiesById.get(user.getId()), user))
                .toList();
    }

}
