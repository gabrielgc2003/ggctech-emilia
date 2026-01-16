package com.ggctech.emilia.services;

import com.ggctech.emilia.exceptions.NotFoundException;
import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.enums.Channel;
import com.ggctech.emilia.model.enums.Status;
import com.ggctech.emilia.repositories.ConsentRepository;
import com.ggctech.emilia.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsentService {
    private final ConsentRepository consentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public void confirm(
            UUID id,
            Channel channel
    ) {

        Consent consent = consentRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Consent not found"));

        // 🔁 Idempotência
        if (consent.getStatus() == Status.ACTIVE) {
            return;
        }

        // ✅ Aceite
        consent.setStatus(Status.ACTIVE);
        consent.setAcceptedAt(Instant.now());
        consent.setChannel(channel);

        consent = consentRepository.save(consent);

        // 🔓 Ativar Subject
        Subject subject = subjectRepository
                .findById(consent.getSubject().getId())
                .orElseThrow();

        subject.setStatus(Status.ACTIVE);
        subjectRepository.save(subject);
    }
}
