package com.huy.spring.service;

import com.huy.spring.domain.Permission;
import com.huy.spring.domain.dto.request.PermissionRequest;
import com.huy.spring.domain.dto.response.PermissionResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.mapper.PermissionMapper;
import com.huy.spring.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper mapper;
    public PermissionResponse handleCreatePermission(PermissionRequest request) {
        if (this.permissionRepository.existsById(request.getName())) {
            throw new AppExeption(ErrorCode.PERMISSION_EXISTED);
        }
        Permission permission = this.mapper.toPermission(request);
        return this.mapper.toResponse(this.permissionRepository.save(permission));
    }
    public PermissionResponse handleUpdatePermission(PermissionRequest request) {
        Permission permission = this.permissionRepository.findById(request.getName())
                .orElseThrow(() -> new AppExeption(ErrorCode.PERMISSION_NOT_FOUND));
        permission.setDescription(request.getDescription());
        return this.mapper.toResponse(this.permissionRepository.save(permission));
    }
    public void handleDeletePermission(String name) {
        Permission permission = this.permissionRepository.findById(name)
                .orElseThrow(() -> new AppExeption(ErrorCode.PERMISSION_NOT_FOUND));
        this.permissionRepository.deleteById(name);
    }
    public List<PermissionResponse> getAllPermission() {
        return this.permissionRepository.findAll().stream().map(mapper::toResponse).toList();
    }
}
