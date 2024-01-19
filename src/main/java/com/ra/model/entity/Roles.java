package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @Column(name = "accounts_account_id")
    private Long accountId;
}
