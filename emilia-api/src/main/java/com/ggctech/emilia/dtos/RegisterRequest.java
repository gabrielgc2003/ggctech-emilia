package com.ggctech.emilia.dtos;

import jakarta.persistence.Column;

public record RegisterRequest(String email, String name, String password, String phone) {
}
