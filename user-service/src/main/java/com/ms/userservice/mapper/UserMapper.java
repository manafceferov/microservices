package com.ms.userservice.mapper;

import com.ms.userservice.dto.UserCreateRequest;
import com.ms.userservice.dto.UserUpdateRequest;
import com.ms.userservice.dto.UserResponse;
import com.ms.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest request);

    User toEntity(UserUpdateRequest request);

    UserResponse toResponse(User user);
}