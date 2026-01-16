package com.ggctech.emilia.services;

import com.ggctech.emilia.model.AccountUser;
import com.ggctech.emilia.model.dtos.auth.AuthResponse;
import com.ggctech.emilia.model.dtos.auth.LoginRequest;
import com.ggctech.emilia.repositories.AccountRepository;
import com.ggctech.emilia.repositories.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountUserRepository accountUserRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        AccountUser accountUser = accountUserRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid credentials"
                ));

        if (!passwordEncoder.matches(request.password(), accountUser.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials"
            );
        }

        String token = jwtService.generateUserToken(accountUser);
        return new AuthResponse(token);
    }
}
