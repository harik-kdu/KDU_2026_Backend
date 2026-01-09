package com.springsecurity.securityDemo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws java.io.IOException, ServletException {
        // JWT validation logic would go here

        String path = request.getServletPath();

        if("/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // For now, just continue the filter chain
        filterChain.doFilter(request, response);
    }
}
