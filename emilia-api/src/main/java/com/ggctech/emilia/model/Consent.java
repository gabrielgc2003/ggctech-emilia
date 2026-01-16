package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.Channel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "consents")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Consent extends BaseModel {
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "consent_version", nullable = false)
    private String consentVersion;

    @Column(name = "accepted_at", nullable = false)
    private Instant acceptedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private Channel channel;

}
