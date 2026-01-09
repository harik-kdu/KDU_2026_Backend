package com.springsecurity.securityDemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public String viewUsers(){
        return "Viewing all users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createUser(){
        return "Creating a new user";
    }
}
