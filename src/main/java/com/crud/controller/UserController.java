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
@RequestMapping("/api/user1")
@RequiredArgsConstructor
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getUserDashboard() {
        logger.info("Accessing User1 Dashboard");
        return ResponseEntity.ok("User1 Dashboard");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        logger.info("Accessing User1 Profile");
        return ResponseEntity.ok("User1 Profile");
    }

    @GetMapping("/track")
    public ResponseEntity<List<ResponseDto>> trackAllUsers() {
        logger.info("Tracking all users");
        return ResponseEntity.ok(userService.trackAllUsers2());
    }

}
