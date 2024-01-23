package com.ra.service.impl;

import com.ra.exception.NotEmptyCustomer;
import com.ra.mapper.ShopMapper;
import com.ra.model.dto.ShopDTO;
import com.ra.model.entity.Shop;
import com.ra.repository.IShopRepository;
import com.ra.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements IShopService {
    @Autowired
    private IShopRepository shopRepository;

    @Override
    public List<ShopDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer {
        Sort sort1 = Sort.by(filed);
        Page<Shop> shopPage = shopRepository.findAllBySearch(search, PageRequest.of(page, limit).withSort(sort1));
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (Shop shop : shopPage) {
            if (shop.isDeleteFlag()) {
                ShopDTO shopDTO = ShopMapper.INSTANCE.shopToShopDTO(shop);
                shopDTOList.add(shopDTO);
            }
        }
        return shopDTOList;
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
    public ShopDTO addShop(ShopDTO shopDTO) {
        try {
            Shop shop = ShopMapper.INSTANCE.ShopDTOToShop(shopDTO);
            Shop saveShop = shopRepository.save(shop);
            return ShopMapper.INSTANCE.shopToShopDTO(saveShop);
        } catch (Exception e) {
            throw new RuntimeException("" + e.getMessage());
        }
    }

    @Override
    public ShopDTO updateShop(ShopDTO shopDTO) throws NotEmptyCustomer {
        try {
            Long shopId = shopDTO.getId();
            if (shopId == null) {
                throw new NotEmptyCustomer("ID Shop không được rỗng");
            }
            Optional<Shop> existingShopOptional = shopRepository.findById(shopId);
            if (existingShopOptional.isPresent()) {
                Shop existingShop = existingShopOptional.get();
                // cập nhật các thuộc tính
                existingShop.setOwnerId(shopDTO.getOwnerId());
                existingShop.setCompanyName(shopDTO.getCompanyName());
                existingShop.setShopName(shopDTO.getShopName());
                existingShop.setShopPhone(shopDTO.getShopPhone());
                existingShop.setOwnerName(shopDTO.getOwnerName());
                existingShop.setOwnerPhone(shopDTO.getOwnerPhone());
                existingShop.setPrefecturesId(shopDTO.getPrefecturesId());
                existingShop.setBrand(shopDTO.getBrand());
                existingShop.setPlans(shopDTO.getPlans());
                existingShop.setZipCode(shopDTO.getZipCode());
                existingShop.setStoreStatus(shopDTO.getStoreStatus());
                existingShop.setDeleteFlag(shopDTO.isDeleteFlag());
                Shop updateShop = shopRepository.save(existingShop);
                return ShopMapper.INSTANCE.shopToShopDTO(updateShop);
            } else {
                throw new NotEmptyCustomer("Shop không được tìm thấy");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi cập nhật " + e.getMessage(), e);
        }
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
