package com.ggctech.emilia.controllers;

import com.ggctech.emilia.model.dtos.consent.ConsentConfirmRequest;
import com.ggctech.emilia.model.enums.Channel;
import com.ggctech.emilia.services.ConsentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/consents")
@RequiredArgsConstructor
public class ConsentController {
    private final ConsentService consentService;

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm(
            @Valid ConsentConfirmRequest consentConfirmRequest,
            HttpServletRequest request
    ) {

        consentService.confirm(
                UUID.fromString(consentConfirmRequest.getConsentId()),
                Channel.valueOf(consentConfirmRequest.getChannel())
        );
        return ResponseEntity.ok().build();
    }
}
