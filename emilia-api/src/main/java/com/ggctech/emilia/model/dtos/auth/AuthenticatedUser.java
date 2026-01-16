package com.ggctech.emilia.model.dtos.auth;

import com.ggctech.emilia.model.enums.Role;

import java.util.UUID;

public record AuthenticatedUser(String userId,
                                UUID accountId,
                                String email,
                                Role role) {

}
