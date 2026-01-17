package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.Domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "summaries")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Summary extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "period_start", nullable = false)
    private Instant periodStart;

    @Column(name = "period_end", nullable = false)
    private Instant periodEnd;

    @Column(name = "structured_content", columnDefinition = "TEXT", nullable = false)
    private String structuredContent;

    @OneToOne
    @JoinColumn(name = "summaryEmbedding", nullable = false)
    private SummaryEmbedding  summaryEmbedding;



}
