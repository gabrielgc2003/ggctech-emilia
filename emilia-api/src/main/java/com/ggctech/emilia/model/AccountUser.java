package com.ggctech.emilia.model;

import com.ggctech.emilia.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountUser extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
