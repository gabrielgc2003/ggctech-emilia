package com.ggctech.emilia.model.dtos.context;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ggctech.emilia.model.dtos.subject.SubjectWithAccountConfigResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationContextResponse {
    private SubjectWithAccountConfigResponse subjectWithAccountConfig;
    private String lastSummary;
    private RecentNarratives recentNarratives;
    private String conversationState;
}
