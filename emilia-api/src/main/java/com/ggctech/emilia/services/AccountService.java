package com.ggctech.emilia.services;

import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.dtos.account.AccountPreRegisterRequest;
import com.ggctech.emilia.model.dtos.account.AccountPreRegisterResponse;
import com.ggctech.emilia.model.enums.Status;
import com.ggctech.emilia.repositories.AccountRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountPreRegisterResponse createAccount(AccountPreRegisterRequest  accountPreRegisterRequest) {
        if (accountRepository.existsAccountByEmail (accountPreRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("Account with this email already exists");
        }
        Account account = new Account();
        account.setEmail(accountPreRegisterRequest.getEmail());
        account.setName(accountPreRegisterRequest.getName());
        account.setPhone(accountPreRegisterRequest.getPhone());
        account.setBillingDay(accountPreRegisterRequest.getBillingDay());
        account.setInviteToken(UUID.randomUUID().toString());
        account.setStatus(Status.PENDING);

        account = accountRepository.save(account);
        return new AccountPreRegisterResponse(account.getId(), account.getInviteToken());
    }
}
