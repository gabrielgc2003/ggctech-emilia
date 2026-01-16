package com.ggctech.emilia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Subject extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne(mappedBy = "subject")
    private Consent consent;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "external_ref", nullable = false)
    private String externalRef;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;

    @OneToMany(mappedBy = "subject")
    private List<Reporter> reporters;

    @OneToMany(mappedBy = "subject")
    private List<Narrative> narratives;

    @OneToMany(mappedBy = "subject")
    private List<Summary> summaries;

    @OneToMany(mappedBy = "subject")
    private List<ConversationSchedule> conversationSchedules;

}
