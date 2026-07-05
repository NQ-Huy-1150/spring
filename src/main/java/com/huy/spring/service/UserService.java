package com.huy.spring.service;

import com.huy.spring.domain.User;
import com.huy.spring.domain.dto.request.UserCreationRequest;
import com.huy.spring.domain.dto.request.UserUpdateRequest;
import com.huy.spring.domain.dto.response.UserResponse;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.mapper.UserMapper;
import com.huy.spring.repository.RoleRepository;
import com.huy.spring.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper mapper;
    public UserResponse createRequest(UserCreationRequest request) {
        if (this.userRepository.existsByUsername(request.getUsername())){
            throw new AppExeption(ErrorCode.USER_EXISTED);
        }
        User user = mapper.toUser(request);
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(request.getPassword()));
        var roles = this.roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return this.mapper.toResponse(this.userRepository.save(user));
    }
    public UserResponse updateRequest(UserUpdateRequest request) {
        Optional<User> optional = this.userRepository.findById(request.getId());
        User user = optional.orElseThrow(() -> new AppExeption(ErrorCode.USER_NOT_FOUND));
        user.setFullName(request.getFirstName() + " " + request.getLastName());
        user.setDob(request.getDob());
        var roles = this.roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return mapper.toResponse(this.userRepository.save(user));
    }
    public boolean deleteRequest(String id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        this.userRepository.deleteById(optional.get().getId());
        return true;
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getAllUsers() {
        log.info("In method get-all-Users");
        return this.userRepository.findAll().stream().map(mapper::toResponse).toList();
    }
    @PreAuthorize("#id == authentication.principal.claims['userId']")
    public UserResponse getUserById(String id) {
        log.info("Get owner's info successfully !");
        return this.mapper.toResponse(
                this.userRepository.findById(id).orElseThrow(
                        () -> new AppExeption(ErrorCode.USER_NOT_FOUND)
                )
        );
    }
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserByUsername(String username) {
        return this.mapper.toResponse(
                this.userRepository.findByUsername(username).orElseThrow(
                        () -> new AppExeption(ErrorCode.USER_NOT_FOUND)
                )
        );
    }
}
