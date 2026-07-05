package com.huy.spring.controller;

import com.huy.spring.domain.dto.request.PermissionRequest;
import com.huy.spring.domain.dto.response.ApiResponse;
import com.huy.spring.domain.dto.response.PermissionResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permission")
public class PermissionController {
    PermissionService permissionService;

    @GetMapping("/permissions")
    public ApiResponse<List<PermissionResponse>> fetchAllPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .message("Get all Permission successfully")
                .response(this.permissionService.getAllPermission())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<PermissionResponse> getCreatePermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .response(this.permissionService.handleCreatePermission(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<PermissionResponse> getUpdatePermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .response(this.permissionService.handleUpdatePermission(request))
                .build();
    }

    @DeleteMapping("/delete/{name}")
    public ApiResponse<Void> getDeletePermission(@PathVariable String name) {
        try {
            this.permissionService.handleDeletePermission(name);
            return ApiResponse.<Void>builder()
                    .code(200)
                    .message("Delete successfully !")
                    .build();
        } catch (AppExeption e) {
            throw new AppExeption(ErrorCode.DELETE_FAILED);
        }
    }
}


