package com.ra.model.dto;

import com.ra.model.entity.Brand;
import com.ra.model.entity.Owner;
import com.ra.model.entity.Plans;
import com.ra.model.entity.Prefectures;
import lombok.*;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShopDTO {
    private Long id;
    private Owner ownerId;
    private String companyName;
    private String shopName;
    private String shopPhone;
    private String ownerName;
    private String ownerPhone;
    private Prefectures prefecturesId;
    private Set<Brand> brand;
    private Set<Plans> plans;
    private Integer zipCode;
    private Boolean storeStatus;
    private Date createDate;
    private Date updateDate;
    private boolean deleteFlag = false;
}
