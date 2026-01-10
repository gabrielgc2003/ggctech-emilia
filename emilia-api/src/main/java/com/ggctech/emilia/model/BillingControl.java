package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.BillingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "billing_controls",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"psychologist_id", "month"}
        ))
public class BillingControl extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    private Psychologist psychologist;

    @Column(nullable = false)
    private YearMonth month;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

}
