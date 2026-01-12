package com.QuickLogistics.QuickLogisticsHub.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.QuickLogistics.QuickLogisticsHub.entity.Users;

@Repository
public class UserRepository {

    private final List<Users> users = new ArrayList<>();
    public UserRepository() {
        users.add(new Users("manager", "manager123"));
        users.get(0).setRole("ROLE_MANAGER");
        users.add(new Users("driver", "driver123"));
        users.get(1).setRole("ROLE_DRIVER");
    }

    public Optional<Users> findByUsername(String username) {
        return users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
    }
    
}
