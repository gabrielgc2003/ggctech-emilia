package com.ggctech.emilia.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "psychologists")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Psychologist extends BaseModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "billing_date", nullable = false)
    private Integer billingDate;

    @OneToMany(mappedBy = "psychologist")
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "psychologist")
    private List<BillingControl> billings = new ArrayList<>();

}
