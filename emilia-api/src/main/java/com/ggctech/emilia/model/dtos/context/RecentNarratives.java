package com.ggctech.emilia.model.dtos.context;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class RecentNarratives {
    private String direction;
    private String content;
}
