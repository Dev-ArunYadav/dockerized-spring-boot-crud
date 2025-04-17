package com.crud.controller;

import com.crud.dto.LoginRequest;
import com.crud.entity.User;
import com.crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        logger.info("Registering user: {}", user.getEmail());
        service.registerUser(user);
        logger.info("User registered successfully: {}", user.getEmail());
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Authenticating user: {}", loginRequest.email());
        String token = service.loginUser(loginRequest);
        logger.info("User authenticated successfully: {}", loginRequest.email());
        return ResponseEntity.ok(token);
    }
}
