package com.huy.spring.repository;

import com.huy.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthenticationRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
