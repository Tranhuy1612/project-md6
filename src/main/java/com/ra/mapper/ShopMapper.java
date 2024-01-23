package com.ra.mapper;

import com.ra.model.dto.ShopDTO;
import com.ra.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopDTO shopToShopDTO(Shop shop);

    Shop ShopDTOToShop(ShopDTO shopDTO);
}
