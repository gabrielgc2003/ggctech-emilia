package com.ggctech.emilia.model.dtos.consent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentConfirmRequest {
    @NotNull
    @NotBlank
    private String consentId;
    private String channel;
}
