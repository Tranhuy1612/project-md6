package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandReq {
    private Long id;
    @NotEmpty(message = "Hãy nhập tên thương hiệu")
    private String brandName;
    @NotEmpty(message = "Hãy nhập đường dẫn URL của thương hiệu ")
    private String brandUrl;
    private boolean usageFlag = false;
    @NotNull(message = "Hãy nhập logo thương hiệu")
    private MultipartFile brandLogo;
    private String storeFlyer;
    private String miniFlyer;
    @CreatedDate
    @NotNull(message = "nhập ngày tạo của thương hiệu")
    private Date createDate;
}
