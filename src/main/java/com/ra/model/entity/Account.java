package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account extends GeneralClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "pass_word")
    private String passWord;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id")
    private Agents agentId;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "activated_flag")
    private Boolean activatedFlag;
}
