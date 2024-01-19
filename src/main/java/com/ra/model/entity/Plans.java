package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Plans extends GeneralClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;
    @Column(name = "plan_name")
    private String planName;
    @Column(name = "max_brand")
    private int maxBrand;
}
