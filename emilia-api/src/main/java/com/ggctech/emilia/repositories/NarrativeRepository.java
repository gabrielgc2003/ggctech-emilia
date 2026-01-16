package com.ggctech.emilia.repositories;

import com.ggctech.emilia.model.Narrative;
import com.ggctech.emilia.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NarrativeRepository extends JpaRepository<Narrative, UUID>{

}
