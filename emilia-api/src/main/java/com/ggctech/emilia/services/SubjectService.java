package com.ggctech.emilia.services;

import com.ggctech.emilia.exceptions.BusinessException;
import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.dtos.consent.ConsentCreatedEvent;
import com.ggctech.emilia.model.dtos.subject.SubjectCreateRequest;
import com.ggctech.emilia.model.dtos.subject.SubjectResponse;
import com.ggctech.emilia.model.enums.Channel;
import com.ggctech.emilia.model.enums.Status;
import com.ggctech.emilia.model.mappers.SubjectMapper;
import com.ggctech.emilia.repositories.AccountRepository;
import com.ggctech.emilia.repositories.ConsentRepository;
import com.ggctech.emilia.repositories.SubjectRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final AccountRepository accountRepository;
    private final ConsentRepository consentRepository;
    private final SubjectMapper subjectMapper;
    private final ApplicationEventPublisher eventPublisher;

    public SubjectResponse create(@Valid SubjectCreateRequest request, UUID accountId) {
        // 1️⃣ Idempotência
        if (subjectRepository.existsByAccountIdAndExternalRef(
                accountId, request.getExternalRef())) {
            throw new BusinessException("Subject already exists");
        }

        // 2️⃣ Criar Subject
        Subject subject = subjectMapper.toEntity(request);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account not found"));
        subject.setAccount(account);
        subject.setName(subject.getName());
        subject.setExternalRef(request.getExternalRef());
        subject.setStatus(Status.PENDING_CONSENT);

        subject = subjectRepository.save(subject);

        // 3️⃣ Criar Consent
        Consent consent = new Consent();
        consent.setSubject(subject);
        consent.setChannel(Channel.API);
        consent.setConsentVersion("v1");
        consent.setStatus(Status.PENDING);

        consent = consentRepository.save(consent);

        // 4️⃣ Publicar evento (AINDA NA TRANSAÇÃO)
        eventPublisher.publishEvent(
                new ConsentCreatedEvent(subject.getId())
        );

        return subjectMapper.toResponse(subject);
    }
}
