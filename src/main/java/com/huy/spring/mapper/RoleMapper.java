package com.huy.spring.mapper;

import com.huy.spring.domain.Role;
import com.huy.spring.domain.dto.request.RoleRequest;
import com.huy.spring.domain.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "rolesPermissions", ignore = true)
    @Mapping(target = "usersRoles", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toResponse (Role role);
}
