package com.ggctech.emilia.model.dtos.consent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ggctech.emilia.model.enums.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentAcceptRequest {
    @NotNull
    @NotBlank
    private String subjectId;

    @NotNull
    @NotBlank
    private Instant acceptedAt;

    @NotNull
    @NotBlank
    private Channel channel;
}
