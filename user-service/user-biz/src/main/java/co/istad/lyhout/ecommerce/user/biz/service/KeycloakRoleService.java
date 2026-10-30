package co.istad.lyhout.ecommerce.user.biz.service;

public interface KeycloakRoleService {

    void assignRoles(String userId, String roleNameWithDelimiter);

}

