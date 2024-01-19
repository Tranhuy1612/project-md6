package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Prefectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefectures_id")
    private Long id;
    @Column(name = "pref_name")
    private String prefecturesName;
    // quận
    @Column(name = "district")
    private String district;
    // phường
    @Column(name = "wards")
    private String wards;
}
