package com.ggctech.emilia.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_prompt_configs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountPromptConfig extends BaseModel{

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(name = "base_prompt", nullable = false, columnDefinition = "TEXT")
    private String basePrompt;

    @Column(name = "conversation_rules", columnDefinition = "TEXT")
    private String conversationRules;

    @Column(name = "forbidden_topics", columnDefinition = "TEXT")
    private String forbiddenTopics;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;
}
