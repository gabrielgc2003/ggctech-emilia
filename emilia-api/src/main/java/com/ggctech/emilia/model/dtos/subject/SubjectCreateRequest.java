package com.ggctech.emilia.model.dtos.subject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectCreateRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String externalRef;
    private String metadata;
}
