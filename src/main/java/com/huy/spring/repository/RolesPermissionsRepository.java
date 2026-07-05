package com.huy.spring.repository;

import com.huy.spring.domain.Roles_Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesPermissionsRepository extends JpaRepository<Roles_Permissions, String> {
}
