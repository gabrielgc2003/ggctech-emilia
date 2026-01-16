package com.ggctech.emilia.services.automation;

import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.dtos.consent.ConsentWebhookPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class N8nClient {
    private final RestTemplate restTemplate;

    @Value("${n8n.webhook.consent}")
    private String consentWebhookUrl;


    public void sendConsent(Subject subject, Consent consent) {
        ConsentWebhookPayload payload =
                new ConsentWebhookPayload(
                        subject.getId(),
                        consent.getId(),
                        subject.getExternalRef()
                );

        restTemplate.postForEntity(
                consentWebhookUrl,
                payload,
                Void.class
        );
    }
}
