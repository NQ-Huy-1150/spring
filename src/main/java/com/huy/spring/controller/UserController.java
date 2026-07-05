package com.huy.spring.controller;

import com.huy.spring.domain.dto.request.UserCreationRequest;
import com.huy.spring.domain.dto.request.UserUpdateRequest;
import com.huy.spring.domain.dto.response.ApiResponse;
import com.huy.spring.domain.dto.response.MessageResponse;
import com.huy.spring.domain.dto.response.UserResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserResponse>> getAllUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}",authentication.getName());
        authentication.getAuthorities().forEach(r -> log.info(r.getAuthority()));
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Get All user successfully !");
        response.setResponse(this.userService.getAllUsers());
        return response;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
    @GetMapping("/whoami")
    public ApiResponse<UserResponse> whoAmI() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AppExeption(ErrorCode.USER_NOT_FOUND);
        }
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .response(this.userService.getUserByUsername(authentication.getName()))
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> getCreateUser(@RequestBody @Valid UserCreationRequest creationRequest) {
        return ResponseEntity.ok(this.userService.createRequest(creationRequest));
    }
    @PutMapping("/update")
    public ApiResponse<UserResponse> getUpdateUser(@RequestBody @Valid UserUpdateRequest updateRequest) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .response(this.userService.updateRequest(updateRequest))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getDeleteUser(@PathVariable String id) {
        boolean status = this.userService.deleteRequest(id);
        if (status) {
            return ResponseEntity.ok(new MessageResponse("Delete successfully !"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found !"));
    }
}
