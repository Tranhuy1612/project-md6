package com.ra.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandDTO {

    private Long id;
    private String brandName;
    private String brandUrl;
    private boolean usageFlag = false;
    @JsonIgnore
    private MultipartFile brandLogoFile;
    private String storeFlyer;
    private String miniFlyer;
    private String brandLogo;
    private boolean deleteFlag = false;
}
