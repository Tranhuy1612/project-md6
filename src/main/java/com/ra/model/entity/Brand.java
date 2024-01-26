package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Brand extends GeneralClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "brand_logo")
    private String brandLogo;
    @Column(name = "brand_url")
    private String brandUrl;
    @Column(name = "store_flyer")
    private String storeFlyer ;
    @Column(name = "mini_flyer")
    private String miniFlyer;
    @Column(name = "usage_flag")
    private boolean usageFlag = true;
}
