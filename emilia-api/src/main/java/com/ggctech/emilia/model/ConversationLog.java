package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversation_logs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationLog extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner", nullable = false)
    private Owner owner;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
}
