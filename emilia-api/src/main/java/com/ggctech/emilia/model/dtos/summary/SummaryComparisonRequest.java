package com.ggctech.emilia.model.dtos.summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryComparisonRequest {
    @NotNull
    @NotBlank
    private String subjectId;

    @NotNull
    @NotBlank
    private String referenceSummaryId;

    @NotNull
    @NotBlank
    private Integer limit;
}
