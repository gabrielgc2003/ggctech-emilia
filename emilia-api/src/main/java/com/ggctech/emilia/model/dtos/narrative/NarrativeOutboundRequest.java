package com.ggctech.emilia.model.dtos.narrative;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NarrativeOutboundRequest {
    @NotNull
    @NotBlank
    private String subjectId;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @NotBlank
    private String Channel;
    private String externalMessageId;
}
