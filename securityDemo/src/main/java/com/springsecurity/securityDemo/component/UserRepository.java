package com.springsecurity.securityDemo.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springsecurity.securityDemo.entities.User;

@Component
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository(PasswordEncoder passwordEncoder) {
        // Predefined users with encoded passwords
        users.add(new User("user1", passwordEncoder.encode("password1"), List.of("ROLE_USER")));
        users.add(new User("user2", passwordEncoder.encode("password2"), List.of("ROLE_ADMIN", "ROLE_USER")));
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
