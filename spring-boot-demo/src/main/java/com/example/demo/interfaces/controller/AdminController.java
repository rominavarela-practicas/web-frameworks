package com.example.demo.interfaces.controller;

import com.example.demo.domain.model.health.HealthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController
{
    @GetMapping("/session")
    public ResponseEntity<HealthDTO> getSessionInfo(Authentication auth)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new HealthDTO("authorized"));
    }
}
