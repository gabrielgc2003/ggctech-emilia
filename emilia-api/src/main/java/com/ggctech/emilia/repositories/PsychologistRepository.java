package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PsychologistRepository extends JpaRepository<Psychologist, UUID> {
    Optional<Psychologist> findByEmail(String email);
}