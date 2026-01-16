package com.ggctech.emilia.controllers;

import com.ggctech.emilia.model.dtos.auth.AuthenticatedUser;
import com.ggctech.emilia.model.dtos.subject.SubjectCreateRequest;
import com.ggctech.emilia.model.dtos.subject.SubjectResponse;
import com.ggctech.emilia.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponse create(
            @RequestBody @Valid SubjectCreateRequest request,
            @AuthenticationPrincipal AuthenticatedUser user
    ) {
        return subjectService.create(request, user.accountId());
    }
}
