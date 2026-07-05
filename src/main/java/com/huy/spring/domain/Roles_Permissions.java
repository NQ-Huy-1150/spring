package com.huy.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles_permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Roles_Permissions {

    @EmbeddedId
    RolePermissionId rolePermissionId = new RolePermissionId();


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleName")
    @JoinColumn(name = "role_name")
    Role role;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionName")
    @JoinColumn(name = "permission_name")
    Permission permission;
}
