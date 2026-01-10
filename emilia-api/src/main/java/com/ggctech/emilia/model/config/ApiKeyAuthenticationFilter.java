package com.ggctech.emilia.model.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    @Value("${emilia.system.api-key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String key = request.getHeader("X-API-KEY");

            if (key == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!apiKey.equals(key)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
                return;
            }

            var authentication = new UsernamePasswordAuthenticationToken(
                    "SYSTEM",
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_SYSTEM"))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}