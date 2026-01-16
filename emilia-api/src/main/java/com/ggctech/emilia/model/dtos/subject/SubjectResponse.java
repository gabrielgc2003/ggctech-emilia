package com.ggctech.emilia.model.dtos.subject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ggctech.emilia.model.enums.Status;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectResponse {
    private String id;
    private String name;
    private String externalRef;
    private Status status;
}
