package com.ggctech.emilia.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal/psychologist")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class PortalPsychologistController {

}
