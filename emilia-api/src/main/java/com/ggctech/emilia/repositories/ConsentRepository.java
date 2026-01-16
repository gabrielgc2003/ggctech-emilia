package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, UUID> {
    Optional<Consent> findBySubjectId(UUID subjectId);
}
