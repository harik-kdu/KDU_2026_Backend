package com.QuickLogistics.QuickLogisticsHub.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.QuickLogistics.QuickLogisticsHub.dto.LoginRequest;
import com.QuickLogistics.QuickLogisticsHub.entity.JwtUtil;
import com.QuickLogistics.QuickLogisticsHub.entity.Users;
import com.QuickLogistics.QuickLogisticsHub.repository.UserRepository;

@RestController
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = new JwtUtil();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        System.out.println("Login attempt for user: " + request.getUsername());
        Users user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }


        return jwtUtil.generateToken(user);
    }
}
