package com.ggctech.emilia.controllers;


import com.ggctech.emilia.model.dtos.auth.AuthResponse;
import com.ggctech.emilia.model.dtos.auth.LoginRequest;
import com.ggctech.emilia.model.dtos.account.AccountUserRegisterRequest;
import com.ggctech.emilia.model.dtos.account.AccountUserRegisterResponse;
import com.ggctech.emilia.services.AccountUserService;
import com.ggctech.emilia.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AccountUserService accountUserService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountUserRegisterResponse> register(@Valid @RequestBody AccountUserRegisterRequest accountUserRegisterRequest) {
        return ResponseEntity.ok(accountUserService.register(accountUserRegisterRequest));
    }
}
