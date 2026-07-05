package com.huy.spring.service;

import com.huy.spring.domain.Role;
import com.huy.spring.domain.dto.request.RoleRequest;
import com.huy.spring.domain.dto.response.RoleResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.mapper.RoleMapper;
import com.huy.spring.repository.PermissionRepository;
import com.huy.spring.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;

    @Transactional
    public RoleResponse handleCreateRole(RoleRequest request) {
        Role role = this.mapper.toRole(request);
        var permissions = this.permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        return this.mapper.toResponse(this.roleRepository.save(role));
    }
    public List<RoleResponse> fetchAllRole(){
        return this.roleRepository.findAll().stream().map(mapper::toResponse).toList();
    }
    public RoleResponse handleUpdateRole(RoleRequest request) {
        Role role = this.roleRepository.findById(request.getName())
                .orElseThrow(() -> new AppExeption(ErrorCode.ROLE_NOT_FOUND));
        var permissions = this.permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role.setDescription(request.getDescription());

        return this.mapper.toResponse(this.roleRepository.save(role));
    }
    public void handleDeleteRole(String name) {
        Role role = this.roleRepository.findById(name)
                .orElseThrow(() -> new AppExeption(ErrorCode.ROLE_NOT_FOUND));
        this.roleRepository.deleteById(name);
    }
    public List<RoleResponse> getAllRole() {
        return this.roleRepository.findAll().stream().map(mapper::toResponse).toList();
    }
}
