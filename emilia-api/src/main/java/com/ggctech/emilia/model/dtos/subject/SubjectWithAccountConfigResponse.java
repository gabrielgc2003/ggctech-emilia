package com.ggctech.emilia.model.dtos.subject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectWithAccountConfigResponse {
    private String subjectId;
    private String subjectName;
    private String subjectStatus;
    private String accountId;
    private String accountName;
    private String accountStatus;
    private String basePrompt;
    private String conversationRules;
    private String forbiddenTopics;
    private String metadata;
    private String configMetadata;

}
