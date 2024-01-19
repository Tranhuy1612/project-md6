package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Owner extends GeneralClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id", nullable = false)
    private Agents agentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone ;
    @Column(name = "company")
    private String company;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code1")
    private int zipCode1;
    @Column(name = "zip_code2")
    private int zipCode2;
    @Column(name = "address")
    private String address;
}
