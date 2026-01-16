package com.ggctech.emilia.model.dtos.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class AccountPreRegisterResponse {
    private UUID accountId;
    private String inviteToken;
}
