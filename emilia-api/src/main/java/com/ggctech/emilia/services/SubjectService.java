package com.ggctech.emilia.services;

import com.ggctech.emilia.exceptions.BusinessException;
import com.ggctech.emilia.model.Account;
import com.ggctech.emilia.model.AccountPromptConfig;
import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.dtos.consent.ConsentCreatedEvent;
import com.ggctech.emilia.model.dtos.subject.SubjectCreateRequest;
import com.ggctech.emilia.model.dtos.subject.SubjectResponse;
import com.ggctech.emilia.model.dtos.subject.SubjectWithAccountConfigResponse;
import com.ggctech.emilia.model.enums.Channel;
import com.ggctech.emilia.model.enums.Domain;
import com.ggctech.emilia.model.enums.Status;
import com.ggctech.emilia.model.mappers.SubjectMapper;
import com.ggctech.emilia.repositories.AccountPromptConfigRepository;
import com.ggctech.emilia.repositories.AccountRepository;
import com.ggctech.emilia.repositories.ConsentRepository;
import com.ggctech.emilia.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final AccountRepository accountRepository;
    private final ConsentRepository consentRepository;
    private final AccountPromptConfigRepository accountPromptConfigRepository;
    private final SubjectMapper subjectMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
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

    public SubjectResponse getSubject(UUID subjectId, UUID accountId) {
        Subject subject = subjectRepository.findByIdAndAccountId(subjectId, accountId)
                .orElseThrow(() -> new BusinessException("Subject not found"));
        return subjectMapper.toResponse(subject);
    }

    public SubjectWithAccountConfigResponse getSubjectWithAccountConfig(String phone, String domain) {
        Subject subject = subjectRepository.findByExternalRefAndAccount_Domain(phone, Domain.valueOf(domain))
                .orElseThrow(() -> new BusinessException("Subject not found"));
        Optional<AccountPromptConfig> optionalConfig = accountPromptConfigRepository.getAccountPromptConfigByAccount(subject.getAccount());
        AccountPromptConfig config = new AccountPromptConfig();
        if (optionalConfig.isPresent()) {
            config = optionalConfig.get();
        }

        SubjectWithAccountConfigResponse response = new SubjectWithAccountConfigResponse();
        response.setSubjectId(subject.getId().toString());
        response.setSubjectName(subject.getName());
        response.setSubjectStatus(subject.getStatus().name());
        response.setAccountId(subject.getAccount().getId().toString());
        response.setAccountName(subject.getAccount().getName());
        response.setAccountStatus(subject.getAccount().getStatus().name());
        response.setBasePrompt(config.getBasePrompt());
        response.setConversationRules(config.getConversationRules());
        response.setForbiddenTopics(config.getForbiddenTopics());
        response.setMetadata(subject.getMetadata());
        response.setConfigMetadata(config.getMetadata());


        return response;
    }

}
