package com.example.demo.interfaces.controller;

import com.example.demo.domain.model.user.SessionInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    @GetMapping("/session")
    public ResponseEntity<SessionInfoDTO> getSessionInfo(Principal principal)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SessionInfoDTO(principal.getName()));
    }
}
