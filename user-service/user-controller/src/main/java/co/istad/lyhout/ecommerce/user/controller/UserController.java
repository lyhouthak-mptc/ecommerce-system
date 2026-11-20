package co.istad.lyhout.ecommerce.user.controller;

import co.istad.lyhout.ecommerce.core.PageResponse;
import co.istad.lyhout.ecommerce.user.dto.UserCreateRequest;
import co.istad.lyhout.ecommerce.user.dto.UserResponse;
import co.istad.lyhout.ecommerce.user.dto.UserUpdateRequest;
import co.istad.lyhout.ecommerce.user.service.UserService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable String id,
                               @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }


    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable String id) {
        return userService.getById(id);
    }


    @GetMapping("/username/{username}")
    public UserResponse getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }


    @GetMapping("/roles/{roleName}")
    public PageResponse<UserResponse> getUsersByRoleName(@PathVariable String roleName,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                         @RequestParam(defaultValue = "15", required = false) int pageSize) {
        return userService.getUsersByRoleName(roleName, PageRequest.of(pageNumber, pageSize));
    }


    @SentinelResource(
            value = "getUsers",
            fallback = "getUsersFallback"
    )
    @GetMapping
    public PageResponse<UserResponse> getUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                               @RequestParam(defaultValue = "15", required = false) int pageSize) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userService.getUsers(PageRequest.of(pageNumber, pageSize));
    }
//
//    public PageResponse<UserResponse> getUsersFallback(int pageNumber, int pageSize) {
//        return null;
//    }
//
//    public PageResponse<UserResponse> getUsersBlocked(int pageNumber,
//                                               int pageSize,
//                                               BlockException e) {
//        IO.print(e.getMessage());
//        return PageResponse.<UserResponse>builder()
//                .items(List.of())
//                .build();
//    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        userService.deleteById(id);
    }


}
