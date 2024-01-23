package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Agents extends GeneralClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long id;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "agent_name")
    private String agentName;
    @Column(name = "charge_name")
    private String chargeName;
    @Column(name = "email")
    private String email;
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "group_agent")
    private String group;
}
