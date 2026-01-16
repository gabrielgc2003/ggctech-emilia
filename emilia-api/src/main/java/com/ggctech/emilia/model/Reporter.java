package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.Channel;
import com.ggctech.emilia.model.enums.ReporterType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "reporters")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Reporter extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    private ReporterType reportType;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private Channel channel;

    @OneToMany(mappedBy = "reporter")
    private List<Narrative> narratives;


}
