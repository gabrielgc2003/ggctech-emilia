package com.ggctech.emilia.services.listener;

import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.dtos.consent.ConsentCreatedEvent;
import com.ggctech.emilia.repositories.ConsentRepository;
import com.ggctech.emilia.repositories.SubjectRepository;
import com.ggctech.emilia.services.automation.N8nClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ConsentAutomationListener {
    private final SubjectRepository subjectRepository;
    private final ConsentRepository consentRepository;
    private final N8nClient n8nClient;

    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(ConsentCreatedEvent event) {

        Subject subject = subjectRepository
                .findById(event.getSubjectId())
                .orElseThrow();

        Consent consent = consentRepository
                .findBySubjectId(subject.getId())
                .orElseThrow();

        try {
            n8nClient.sendConsent(subject, consent);
        } catch (Exception ex) {
            // Log the exception but do not rethrow to avoid affecting the main transaction
            System.err.println("Failed to send consent to N8n: " + ex.getMessage());
        }
    }
}
