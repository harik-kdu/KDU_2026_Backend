package com.Library.LibrarySystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfig {
    
    @Bean
    public UserDetailsService userDetailService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("librarian")
            .password(passwordEncoder().encode("lib123"))
            .roles("LIBRARIAN")
            .build(),
            User.withUsername("member")
            .password(passwordEncoder().encode("mem123"))
            .roles("MEMBER")
            .build()
        );
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
