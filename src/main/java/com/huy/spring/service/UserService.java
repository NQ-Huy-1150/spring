package com.huy.spring.service;

import com.huy.spring.domain.User;
import com.huy.spring.domain.dto.request.UserCreationRequest;
import com.huy.spring.domain.dto.request.UserUpdateRequest;
import com.huy.spring.domain.dto.response.UserResponse;
import com.huy.spring.enums.Role;
import com.huy.spring.exception.AppExeption;
import com.huy.spring.exception.ErrorCode;
import com.huy.spring.mapper.UserMapper;
import com.huy.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    public UserResponse createRequest(UserCreationRequest request) {
        if (this.userRepository.existsByUsername(request.getUsername())){
            throw new AppExeption(ErrorCode.USER_EXISTED);
        }
        User user = mapper.toUser(request);
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return this.mapper.toResponse(this.userRepository.save(user));
    }
    public void updateRequest(UserUpdateRequest request) {
        Optional<User> optional = this.userRepository.findById(request.getId());
        User user = optional.orElseThrow(() -> new AppExeption(ErrorCode.USER_NOT_FOUND));
        String newFullName = request.getFirstName() + " " + request.getLastName();
        if (!user.getFullName().equals(newFullName)) {
            user.setFullName(newFullName);
        }
        if(user.getDob() != request.getDob()) {
            user.setDob(request.getDob());
        }
        this.userRepository.save(user);
    }
    public boolean deleteRequest(String id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        this.userRepository.deleteById(optional.get().getId());
        return true;
    }
    public List<UserResponse> getAllUsers() {
        return this.userRepository.findAll().stream().map(mapper::toResponse).toList();
    }
    public UserResponse getUserById(String id) {
        Optional<User> optional = this.userRepository.findById(id);
        return mapper.toResponse(optional.orElseThrow(() -> new AppExeption(ErrorCode.USER_NOT_FOUND)));
    }
}
