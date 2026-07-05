package com.huy.spring.repository;

import com.huy.spring.domain.Users_Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository<Users_Roles, String> {
}
