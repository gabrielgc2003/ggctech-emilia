package com.ggctech.emilia.config;

import com.ggctech.emilia.model.AccountUser;
import com.ggctech.emilia.model.dtos.auth.AuthenticatedUser;
import com.ggctech.emilia.model.enums.Role;
import com.ggctech.emilia.repositories.AccountUserRepository;
import com.ggctech.emilia.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AccountUserRepository accountUserRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtService.parseToken(header.substring(7));

            if (!"USER".equals(claims.get("role"))) {
                response.sendError(HttpStatus.FORBIDDEN.value(), "USER role required");
                return;
            }

            AccountUser accountUser = accountUserRepository.findById(
                    UUID.fromString(claims.getSubject())
            ).orElseThrow(() -> new RuntimeException("User not found"));

            AuthenticatedUser user = new AuthenticatedUser(
                    accountUser.getId().toString(),
                    accountUser.getAccount().getId(),
                    accountUser.getEmail(),
                    Role.valueOf(claims.get("role").toString())
            );
            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT");
        }
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/v1/auth")
                || request.getRequestURI().startsWith("/health");
    }
}
