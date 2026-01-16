package com.ggctech.emilia.model.dtos.consent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentResponse {
    private UUID id;
    private String version;
    private String status;
    private LocalDateTime expiresAt;
}
