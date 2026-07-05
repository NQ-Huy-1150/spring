package com.huy.spring.mapper;

import com.huy.spring.domain.Permission;
import com.huy.spring.domain.dto.request.PermissionRequest;
import com.huy.spring.domain.dto.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toResponse (Permission permission);
}
