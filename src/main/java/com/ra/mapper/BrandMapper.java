package com.ra.mapper;

import com.ra.model.dto.BrandDTO;
import com.ra.model.entity.Brand;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    BrandDTO brandToBrandDTO(Brand brand);
    Brand brandDTOToBrand(BrandDTO brandDTO);
}
