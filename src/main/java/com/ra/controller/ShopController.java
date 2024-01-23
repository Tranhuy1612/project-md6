package com.ra.controller;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.ShopDTO;
import com.ra.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    // lấy thông tin shop theo id
    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getById(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }

    // Xóa Shop theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(shopService.delete(id), HttpStatus.OK);
    }
}
