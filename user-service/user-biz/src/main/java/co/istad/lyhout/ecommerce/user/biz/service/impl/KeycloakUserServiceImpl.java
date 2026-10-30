package co.istad.lyhout.ecommerce.user.biz.service.impl;

import co.istad.lyhout.ecommerce.user.biz.config.KeycloakProperties;
import co.istad.lyhout.ecommerce.user.biz.exception.KeycloakException;
import co.istad.lyhout.ecommerce.user.biz.service.KeycloakUserService;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    private UsersResource usersResource() {
        return keycloak.realm(keycloakProperties.realm()).users();
    }

    @Override
    public void createKeycloakUser(UserRepresentation userRepresentation) {
        try (Response response = usersResource().create(userRepresentation)) {
            int status = response.getStatus();
            log.info("Keycloak response: {}", status);
            if (status != HttpStatus.CREATED.value()) {
                // 409 = username or email already exists in Keycloak
                HttpStatus httpStatus = status == HttpStatus.CONFLICT.value()
                        ? HttpStatus.CONFLICT
                        : HttpStatus.BAD_GATEWAY;
                throw new KeycloakException(httpStatus,
                        "Create user in Keycloak failed with status " + status);
            }
            log.info("Created user in Keycloak");
        }
    }

    @Override
    public List<UserRepresentation> getUsersByRoleName(String roleName) {
        try {
            return keycloak.realm(keycloakProperties.realm())
                    .roles()
                    .get(roleName)
                    .getUserMembers();
        } catch (NotFoundException e) {
            throw new KeycloakException(HttpStatus.NOT_FOUND,
                    "Role " + roleName + " not found in Keycloak");
        }
    }

    @Override
    public List<UserRepresentation> getUsers() {
        return usersResource().list();
    }

    @Override
    public UserRepresentation getUserById(String id) {
        try {
            return usersResource().get(id).toRepresentation();
        } catch (NotFoundException e) {
            throw new KeycloakException(HttpStatus.NOT_FOUND, "User in Keycloak not found");
        }
    }

    @Override
    public UserRepresentation getUserByUsername(String username) {
        return usersResource()
                .search(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new KeycloakException(
                        HttpStatus.NOT_FOUND,
                        "User in Keycloak not found"
                ));
    }

    @Override
    public void updateUser(String id, UserRepresentation userRepresentation) {
        try {
            usersResource().get(id).update(userRepresentation);
            log.info("Updated user {} in Keycloak", id);
        } catch (NotFoundException e) {
            throw new KeycloakException(HttpStatus.NOT_FOUND, "User in Keycloak not found");
        } catch (ClientErrorException e) {
            // 409 = email already used by another user
            int status = e.getResponse().getStatus();
            HttpStatus httpStatus = status == HttpStatus.CONFLICT.value()
                    ? HttpStatus.CONFLICT
                    : HttpStatus.BAD_GATEWAY;
            throw new KeycloakException(httpStatus,
                    "Update user in Keycloak failed with status " + status);
        }
    }

    @Override
    public void deleteUser(String id) {
        try (Response response = usersResource().delete(id)) {
            int status = response.getStatus();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new KeycloakException(HttpStatus.NOT_FOUND, "User in Keycloak not found");
            }
            if (status != HttpStatus.NO_CONTENT.value()) {
                throw new KeycloakException(HttpStatus.BAD_GATEWAY,
                        "Delete user in Keycloak failed with status " + status);
            }
            log.info("Deleted user {} in Keycloak", id);
        }
    }
}
