package com.ggctech.emilia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Account extends BaseModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "billing_day")
    private Integer billingDay;

    @Column(name = "invite_token", unique = true)
    private String inviteToken;

    @OneToOne(mappedBy = "account")
    private AccountPromptConfig accountPromptConfig;

    @OneToMany(mappedBy = "account")
    private List<AccountUser> accountUsers = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<BillingAccount> billingAccounts = new ArrayList<>();


}
