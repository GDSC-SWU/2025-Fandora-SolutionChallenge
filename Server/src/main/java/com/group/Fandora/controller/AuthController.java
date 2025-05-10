package com.group.Fandora.controller;

import com.group.Fandora.dto.LoginResponse;
import com.group.Fandora.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> authenticateWithGoogle(@RequestBody Map<String, String> request) {
        String idToken = request.get("idToken");
        try {
            String jwtToken = authService.authenticate(idToken);
            LoginResponse response = new LoginResponse(jwtToken);
            return ResponseEntity.ok(response);
        } catch (GeneralSecurityException | IOException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}