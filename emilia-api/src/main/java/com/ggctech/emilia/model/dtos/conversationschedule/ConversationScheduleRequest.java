package com.ggctech.emilia.model.dtos.conversationschedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationScheduleRequest {
    private String subjectId;
    private Integer dayOfWeek;
    private String startTime;
    private boolean active;
}
