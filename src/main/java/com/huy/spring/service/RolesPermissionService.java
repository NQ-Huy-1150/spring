package com.huy.spring.service;

import com.huy.spring.domain.Permission;
import com.huy.spring.domain.Role;
import com.huy.spring.domain.Roles_Permissions;
import com.huy.spring.domain.dto.request.RoleRequest;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.repository.PermissionRepository;
import com.huy.spring.repository.RoleRepository;
import com.huy.spring.repository.RolesPermissionsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RolesPermissionService {
    RolesPermissionsRepository rolesPermissionsRepository;
    PermissionRepository permissionRepository;
    RoleRepository roleRepository;
    public void handleCreate(Role role, RoleRequest request) {
        Set<Permission> permissions = new HashSet<>();
        request.getPermissions().forEach(per -> permissions.add(this.permissionRepository.findById(per)
                .orElseThrow(() -> new AppExeption(ErrorCode.PERMISSION_NOT_FOUND))
        ));

        try {
            for (Permission per : permissions) {
                Roles_Permissions rolesPermissions = Roles_Permissions.builder()
                        .role(role)
                        .permission(per)
                        .build();
                this.rolesPermissionsRepository.save(rolesPermissions);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
