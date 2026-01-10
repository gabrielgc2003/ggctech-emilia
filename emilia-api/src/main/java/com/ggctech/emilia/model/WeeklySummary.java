package com.ggctech.emilia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "weekly_summaries",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"patient_id", "week_start", "week_end"}
        ))
public class WeeklySummary extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "week_start", nullable = false)
    private LocalDate weekStart;

    @Column(name = "week_end", nullable = false)
    private LocalDate weekEnd;

    @Column(name = "summary_text", columnDefinition = "TEXT", nullable = false)
    private String summaryText;
}
