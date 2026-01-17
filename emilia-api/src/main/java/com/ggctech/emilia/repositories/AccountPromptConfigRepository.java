package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.AccountPromptConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountPromptConfigRepository extends JpaRepository<AccountPromptConfig, UUID> {
    Optional<AccountPromptConfig> getAccountPromptConfigByAccount(Account account);
}
