package com.ra.controller;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.BrandDTO;
import com.ra.model.dto.ShopDTO;
import com.ra.model.entity.Shop;
import com.ra.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping()
    public ResponseEntity<List<ShopDTO>> getAllBrand(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "id") String filed,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int limit
    ) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.findAllShow(search, filed, sort, page, limit), HttpStatus.OK);
    }

    // lấy thông tin shop theo id
    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getById(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }

    // Thêm mới Shop
    @PostMapping("/{id}")
    public ResponseEntity<ShopDTO> getById(@ModelAttribute ShopDTO shopDTO) {
        return new ResponseEntity<>(shopService.addShop(shopDTO), HttpStatus.CREATED);
    }

    //Chỉnh sửa thông tin shop theo id
    @PutMapping("/update/{id}")
    public ResponseEntity<ShopDTO> updateShop(@ModelAttribute ShopDTO shopDTO) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.updateShop(shopDTO), HttpStatus.OK);
    }

    // Xóa Shop theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.delete(id), HttpStatus.OK);
    }
}
