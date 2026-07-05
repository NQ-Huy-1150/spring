package com.huy.spring.controller;

import com.huy.spring.domain.dto.request.RoleRequest;
import com.huy.spring.domain.dto.response.ApiResponse;
import com.huy.spring.domain.dto.response.RoleResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;
    @GetMapping("/roles")
    public ApiResponse<List<RoleResponse>> fetchAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .message("Get all Role successfully")
                .response(this.roleService.getAllRole())
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<RoleResponse> getCreateRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .response(this.roleService.handleCreateRole(request))
                .build();
    }
    @PutMapping("/update")
    public ApiResponse<RoleResponse> getUpdateRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .response(this.roleService.handleUpdateRole(request))
                .build();
    }
    @DeleteMapping("/delete/{name}")
    public ApiResponse getDeleteRole(@PathVariable String name) {
        try {
            this.roleService.handleDeleteRole(name);
            return ApiResponse.builder()
                    .code(200)
                    .message("Delete successfully !")
                    .build();
        } catch (AppExeption e) {
            throw new AppExeption(ErrorCode.DELETE_FAILED);
        }
    }
}
