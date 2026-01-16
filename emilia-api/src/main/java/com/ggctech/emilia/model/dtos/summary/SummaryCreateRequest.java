package com.ggctech.emilia.model.dtos.summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ggctech.emilia.model.enums.Domain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties
public class SummaryCreateRequest {
    @NotNull
    @NotBlank
    private String subjectId;

    @NotNull
    @NotBlank
    private Domain domain;

    @NotNull
    @NotBlank
    private Instant periodStart;

    @NotNull
    @NotBlank
    private Instant periodEnd;

    @NotNull
    @NotBlank
    private String structuredContent;
}
