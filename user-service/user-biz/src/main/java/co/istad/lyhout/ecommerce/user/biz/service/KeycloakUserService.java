package co.istad.lyhout.ecommerce.user.biz.service;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakUserService {

    void createKeycloakUser(UserRepresentation userRepresentation);

    List<UserRepresentation> getUsersByRoleName(String roleName);

    List<UserRepresentation> getUsers();

    UserRepresentation getUserById(String id);

    UserRepresentation getUserByUsername(String username);

    void updateUser(String id, UserRepresentation userRepresentation);

    void deleteUser(String id);
}
