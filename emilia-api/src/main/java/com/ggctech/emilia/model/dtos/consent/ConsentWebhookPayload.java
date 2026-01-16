package com.ggctech.emilia.model.dtos.consent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentWebhookPayload {
    private UUID subjectId;
    private UUID consentId;
    private String externalRef;
}
