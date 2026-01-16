package com.ggctech.emilia.model.dtos.narrative;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ggctech.emilia.model.enums.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NarrativeBatchRequest {
    @NotNull
    @NotBlank
    private String subjectId;

    @NotEmpty
    private List<String> messages;

    @NotNull
    @NotBlank
    private Channel channel;

    private Integer windowSeconds;

    @NotEmpty
    private List<String> externalMessageIds;
}
