package com.crud.controller;

import com.crud.dto.ResponseDto;
import com.crud.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final AdminService service;

     @GetMapping("/dashboard")
     public ResponseEntity<String> getAdminDashboard() {
        logger.info("Accessing Admin Dashboard");
        return ResponseEntity.ok("Admin Dashboard");
    }

    @GetMapping("/track")
    public ResponseEntity<List<ResponseDto>> trackAllUsers() {
        logger.info("Tracking all users");
        return ResponseEntity.ok(service.trackAllUsers());
    }
}
