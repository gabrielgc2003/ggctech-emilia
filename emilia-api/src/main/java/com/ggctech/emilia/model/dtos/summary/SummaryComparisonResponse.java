package com.ggctech.emilia.model.dtos.summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryComparisonResponse {
    private String currentSummary;
    private String similarSummary;
    private String insights;
}
