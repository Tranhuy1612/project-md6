package com.ra.service;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.ShopDTO;

import java.io.IOException;
import java.util.List;

public interface IShopService  {
    List<ShopDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer;

    ShopDTO findById(Long id) throws NotEmptyCustomer;

    ShopDTO addShop(ShopDTO shopDTO) throws IOException;
    ShopDTO updateShop(ShopDTO shopDTO) throws NotEmptyCustomer;
    String delete(Long id)throws NotEmptyCustomer;
}
