package com.ra.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandDTO {

//    @JsonProperty("id")
    private Long id;
    private String brandName;
    private String brandUrl;
    private boolean usageFlag = true;
    @JsonIgnore
    private MultipartFile brandLogoFile;
    private String storeFlyer;
    private String miniFlyer;
    private String brandLogo;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date updateDate;
    private boolean deleteFlag = false;
}
