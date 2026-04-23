package com.ms.userservice.service;

import com.ms.userservice.client.ProductClient;
import com.ms.userservice.dto.UserCreateRequest;
import com.ms.userservice.dto.UserUpdateRequest;
import com.ms.userservice.dto.UserResponse;
import com.ms.userservice.entity.User;
import com.ms.userservice.mapper.UserMapper;
import com.ms.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProductClient productClient;

    public UserService(UserRepository userRepository, UserMapper userMapper, ProductClient productClient) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.productClient = productClient;
    }

    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = userMapper.toEntity(request);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse update(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPassword() != null) user.setPassword(request.getPassword());

        return userMapper.toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public Object getUserWithProducts(Long userId) {
        UserResponse user = getById(userId);
        Object products = productClient.getAllProducts().getData();

        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("user", user);
        result.put("products", products);
        return result;
    }
}