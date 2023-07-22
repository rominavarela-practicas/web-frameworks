package com.example.demo.interfaces.controller;

import com.example.demo.domain.model.user.SessionInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController
{
    @GetMapping("/session")
    public ResponseEntity<SessionInfoDTO> getSessionInfo(Authentication auth)
    {
        var authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SessionInfoDTO.builder()
                        .subject(auth.getName())
                        .authorities(authorities)
                        .authentication(auth)
                        .build());
    }
}
