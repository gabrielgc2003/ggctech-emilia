package com.ggctech.emilia.controllers;

import com.ggctech.emilia.model.dtos.subject.SubjectWithAccountConfigResponse;
import com.ggctech.emilia.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/subjects")
@RequiredArgsConstructor
public class SystemSubjectController {
    private final SubjectService subjectService;
    @GetMapping
    public SubjectWithAccountConfigResponse getSubject(@Param("phone") String phone, @Param("domain") String domain) {
        return subjectService.getSubjectWithAccountConfig(phone, domain);
    }
}
