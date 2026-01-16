package com.ggctech.emilia.model.mappers;

import com.ggctech.emilia.model.Subject;
import com.ggctech.emilia.model.dtos.subject.SubjectCreateRequest;
import com.ggctech.emilia.model.dtos.subject.SubjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectCreateRequest request);

    @Mapping(target = "status", expression = "java(subject.getStatus())")
    SubjectResponse toResponse(Subject subject);
}