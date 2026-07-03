package com.huy.spring.mapper;

import com.huy.spring.domain.User;
import com.huy.spring.domain.dto.request.UserCreationRequest;
import com.huy.spring.domain.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "fullName", expression = "java(request.getFirstName() + \" \" + request.getLastName())")
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toResponse (User user);
}
