package com.example.demo.interfaces.controller;

import com.example.demo.domain.model.health.HealthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/health")
public class HealthController
{
    @GetMapping("")
    public ResponseEntity<HealthDTO> getHealth()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new HealthDTO("happy"));
    }
}
