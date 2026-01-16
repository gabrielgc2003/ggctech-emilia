package com.ggctech.emilia.model;


import com.ggctech.emilia.model.enums.Direction;
import com.ggctech.emilia.model.enums.NarrativeType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "narratives")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Narrative extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private Reporter reporter;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    private Direction direction;

    @Enumerated(EnumType.STRING)
    @Column(name = "narrative_type", nullable = false)
    private NarrativeType narrativeType;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "external_message_id")
    private String externalMessageId;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
}
