package co.istad.lyhout.ecommerce.user.controller;

import co.istad.lyhout.ecommerce.user.biz.service.UserService;
import co.istad.lyhout.ecommerce.user.dto.UserCreateRequest;
import co.istad.lyhout.ecommerce.user.dto.UserResponse;
import co.istad.lyhout.ecommerce.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/roles/{roleName}")
    public List<UserResponse> getUsersByRoleName(@PathVariable String roleName) {
        return userService.getUsersByRoleName(roleName);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id,
                                   @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(id, userUpdateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

}
