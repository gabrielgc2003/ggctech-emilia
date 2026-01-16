package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, UUID> {
    boolean existsByEmail(String email);

    Optional<AccountUser> findByEmail(String email);
}
