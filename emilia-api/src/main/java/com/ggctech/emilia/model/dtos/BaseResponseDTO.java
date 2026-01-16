package com.ggctech.emilia.model.dtos;

import com.ggctech.emilia.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseResponseDTO {
    private UUID id;
    private Instant createdAt;
    private Status status;
}
