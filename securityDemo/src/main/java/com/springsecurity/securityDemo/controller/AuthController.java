package com.springsecurity.securityDemo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.securityDemo.component.JwtUtil;
import com.springsecurity.securityDemo.component.UserRepository;
import com.springsecurity.securityDemo.entities.User;

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
        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }
}
