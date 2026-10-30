package co.istad.lyhout.ecommerce.user.biz.service;

import co.istad.lyhout.ecommerce.user.dto.UserCreateRequest;
import co.istad.lyhout.ecommerce.user.dto.UserResponse;
import co.istad.lyhout.ecommerce.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    List<UserResponse> getUsersByRoleName(String roleName);

    List<UserResponse> getUsers();

    UserResponse getUserById(String id);

    UserResponse getUserByUsername(String username);

    UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest);

    void deleteUser(String id);

}
