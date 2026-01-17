package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.enums.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findByIdAndAccount(UUID subjectId, Account account);

    Optional<Subject> findByExternalRefAndAccount(String externalRef, Account account);

    boolean existsByAccountIdAndExternalRef(UUID subjectId, String externalRef);

    Optional<Subject> findByIdAndAccountId(UUID subjectId, UUID accountId);


    Optional<Subject> findByExternalRefAndAccount_Domain(String externalRef, Domain domain);
}
