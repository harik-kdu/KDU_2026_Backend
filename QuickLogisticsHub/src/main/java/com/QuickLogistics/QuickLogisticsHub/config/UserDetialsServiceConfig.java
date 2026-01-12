package com.QuickLogistics.QuickLogisticsHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetialsServiceConfig {
    
    @Bean
    public UserDetailsService userDetailService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("manager")
                .roles("MANAGER")
                .password(passwordEncoder().encode("manager123"))
                .build(),
            User.withUsername("driver")
                .roles("DRIVER")
                .password(passwordEncoder().encode("driver123"))
                .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
