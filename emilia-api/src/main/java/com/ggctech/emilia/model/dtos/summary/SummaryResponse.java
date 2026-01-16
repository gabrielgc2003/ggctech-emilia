package com.ggctech.emilia.model.dtos.summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@JsonIgnoreProperties
public class SummaryResponse {
    private String id;
    private Instant periodStart;
    private Instant periodEnd;
    private String structuredContent;
}
