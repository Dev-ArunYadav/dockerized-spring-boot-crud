package com.crud.service;

import com.crud.dto.LoginRequest;
import com.crud.dto.ResponseDto;
import com.crud.entity.User;
import com.crud.enums.UserRole;
import com.crud.repository.UserRepository;
import com.crud.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtil;
    private final ModelMapper mapper;

    public void registerUser(User user) {
        logger.info("Registering user with email: {}", user.getEmail());
        if ("ADMIN".equals(user.getRole().name()) && userRepository.existsByRole(user.getRole())) {
            logger.error("Admin registration failed. An admin is already registered.");
            throw new RuntimeException("An admin is already registered. Only one admin is allowed.");
        }
        user.setRole(UserRole.valueOf(user.getRole().toString().toUpperCase()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User registered successfully with email: {}", user.getEmail());
    }

    public String loginUser(LoginRequest loginRequest) {
        logger.info("Attempting login for email: {}", loginRequest.email());
        User user = userRepository.findByEmail(loginRequest.email());

        if (user == null) {
            logger.error("Login failed. User not found with email: {}", loginRequest.email());
            throw new UsernameNotFoundException("User not found with email: " + loginRequest.email());
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            logger.error("Login failed. Invalid credentials for email: {}", loginRequest.email());
            throw new RuntimeException("Invalid Credentials");
        }

        logger.info("Login successful for email: {}", loginRequest.email());
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

   public List<ResponseDto> trackAllUsers2() {
        logger.info("Fetching all users with role USER2");
        List<User> users = userRepository.findByRoleIn(List.of(UserRole.USER2));
        List<ResponseDto> response = users.stream()
                .map(user -> mapper.map(user, ResponseDto.class))
                .toList();
        logger.info("Fetched {} users with role USER2", response.size());
        return response;
   }
}
