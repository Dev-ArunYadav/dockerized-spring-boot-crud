package com.crud.controller;

import com.crud.dto.ResponseDto;
import com.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user2")
@RequiredArgsConstructor
public class LastUserController {
    private static final Logger logger = LoggerFactory.getLogger(LastUserController.class);

    private final UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getUserDashboard() {
        logger.info("Accessing User2 Dashboard");
        return ResponseEntity.ok("User2 Dashboard");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        logger.info("Accessing User2 Profile");
        return ResponseEntity.ok("User2 Profile");
    }
}
