package com.ggctech.emilia.model;

import com.ggctech.emilia.config.FloatArrayConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "summary_embeddings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SummaryEmbedding extends BaseModel {
    @OneToOne
    @JoinColumn(name = "summary_id", nullable = false)
    private Summary summary;

    @Column(name = "embedding", columnDefinition = "float4[]", nullable = false)
    @Convert(converter = FloatArrayConverter.class)
    private float[] embedding;

}
