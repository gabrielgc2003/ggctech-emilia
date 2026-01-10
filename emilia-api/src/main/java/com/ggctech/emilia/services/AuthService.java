package com.ggctech.emilia.services;

import com.ggctech.emilia.dtos.AuthResponse;
import com.ggctech.emilia.dtos.LoginRequest;
import com.ggctech.emilia.dtos.RegisterRequest;
import com.ggctech.emilia.model.Psychologist;
import com.ggctech.emilia.repositories.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PsychologistRepository psychologistRepository;
    private final PasswordEncoder passwordEncoder;
    private final PsychologistRepository repository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        Psychologist psychologist = psychologistRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid credentials"
                ));

        if (!passwordEncoder.matches(request.password(), psychologist.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials"
            );
        }

        String token = jwtService.generateUserToken(psychologist);

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        var psychologistExists = repository
                .findByEmail(request.email())
                .isPresent();
        if (psychologistExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already in use"
            );
        }
        Psychologist psychologist = new Psychologist();
        psychologist.setEmail(request.email());
        psychologist.setPassword(
                passwordEncoder.encode(request.password())
        );
        psychologist.setName(request.name());
        psychologist.setPhone(request.phone());

        var psychologistCreated = repository.save(psychologist);
        String token = jwtService.generateUserToken(psychologistCreated);
        return new AuthResponse(token);
    }


}

