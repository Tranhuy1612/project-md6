package com.ra.service.impl;

import com.ra.exception.NotEmptyCustomer;
import com.ra.mapper.ShopMapper;
import com.ra.model.dto.ShopDTO;
import com.ra.model.entity.Shop;
import com.ra.repository.IShopRepository;
import com.ra.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements IShopService {
    @Autowired
    private IShopRepository shopRepository;

    @Override
    public List<ShopDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer {
        return null;
    }

    @Override
    public ShopDTO findById(Long id) throws NotEmptyCustomer {
        Optional<Shop> s = shopRepository.findById(id);
        if (s.isPresent() && s.get().isDeleteFlag()) {
            return ShopMapper.INSTANCE.shopToShopDTO(s.get());
        }
        throw new NotEmptyCustomer("Không tìm thấy shop");
    }


    @Override
    public ShopDTO addShop(ShopDTO shopDTO) throws IOException {
        return null;
    }

    @Override
    public ShopDTO updateShop(ShopDTO shopDTO) throws NotEmptyCustomer {
        return null;
    }

    @Override
    public String delete(Long id) throws NotEmptyCustomer {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent() && shop.get().isDeleteFlag()) {
            Shop shop1 = shop.get();
            shop1.setDeleteFlag(false);
            shopRepository.save(shop1);
            return "Xóa thành công !";
        }
        throw new NotEmptyCustomer("Shop Không tồn tại");
    }
}
