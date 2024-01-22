package com.ra.model.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandRes {
    private Long id;
    private String brandName;
    private String brandUrl;
    private boolean usageFlag = false;
    private String brandLogo;
    private String storeFlyer;
    private String miniFlyer;
    private Date createDate;
}
