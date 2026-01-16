package com.ggctech.emilia.model.mappers;

import com.ggctech.emilia.model.Consent;
import com.ggctech.emilia.model.dtos.consent.ConsentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsentMapper {
    @Mapping(target = "status", expression = "java(consent.getStatus().name())")
    ConsentResponse toResponse(Consent consent);
}