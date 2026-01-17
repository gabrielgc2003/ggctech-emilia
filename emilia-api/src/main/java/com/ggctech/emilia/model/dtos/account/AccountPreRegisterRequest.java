package com.ggctech.emilia.model.dtos.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountPreRegisterRequest(@NotNull
                                      @NotBlank
                                      String name,
                                      @NotNull
                                      @NotBlank
                                      @Email
                                      String email,
                                      @NotNull
                                      @NotBlank
                                      String phone,
                                      @NotNull
                                      @NotBlank
                                      String domain,
                                      Integer billingDay) {

}
