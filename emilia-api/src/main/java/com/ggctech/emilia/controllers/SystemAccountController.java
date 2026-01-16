package com.ggctech.emilia.controllers;

import com.ggctech.emilia.model.dtos.account.AccountPreRegisterRequest;
import com.ggctech.emilia.model.dtos.account.AccountPreRegisterResponse;
import com.ggctech.emilia.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/accounts")
@RequiredArgsConstructor
public class SystemAccountController {

    private final AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<AccountPreRegisterResponse> createAccount(@RequestBody  @Valid AccountPreRegisterRequest accountPreRegisterRequest) {
        // Placeholder implementation
        return ResponseEntity.ok(accountService.createAccount(accountPreRegisterRequest));
    }
}
