package com.ggctech.emilia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends BaseModel{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    private Psychologist psychologist;

    @OneToMany(mappedBy = "patient")
    private List<ConversationLog> conversationLogs = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<WeeklySummary> weeklySummaries = new ArrayList<>();


}
