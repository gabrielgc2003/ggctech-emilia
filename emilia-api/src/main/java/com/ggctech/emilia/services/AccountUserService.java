package com.ggctech.emilia.services;

import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.AccountUser;
import com.ggctech.emilia.model.dtos.account.AccountUserRegisterRequest;
import com.ggctech.emilia.model.dtos.account.AccountUserRegisterResponse;
import com.ggctech.emilia.model.enums.Role;
import com.ggctech.emilia.model.enums.Status;
import com.ggctech.emilia.repositories.AccountRepository;
import com.ggctech.emilia.repositories.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUserService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    private final PasswordEncoder passwordEncoder;
    public AccountUserRegisterResponse register(AccountUserRegisterRequest accountUserRegisterRequest) {
        Account account = accountRepository.getByInviteToken(accountUserRegisterRequest.getInviteToken()).orElseThrow(
                () -> new IllegalArgumentException("Invalid invite token")
        );
        if(accountUserRepository.existsByEmail(accountUserRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("User already registered");
        }

        AccountUser accountUser = new AccountUser();
        accountUser.setEmail(accountUserRegisterRequest.getEmail());
        accountUser.setPassword(passwordEncoder.encode(accountUserRegisterRequest.getPassword()));
        accountUser.setRole(Role.USER);
        accountUser.setAccount(account);
        accountUser = accountUserRepository.save(accountUser);

        if (accountUser.getId() != null) {
            if(account.getStatus().name().equals("PENDING")) {
                account.setStatus(Status.ACTIVE);
                accountRepository.save(account);
            }
        }
        return new AccountUserRegisterResponse(
                accountUser.getId(),
                accountUser.getAccount().getId()
        );
    }
}
