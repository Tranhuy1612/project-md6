package com.ra.controller;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.request.BrandReq;
import com.ra.model.dto.response.BrandRes;
import com.ra.service.impl.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<BrandRes>> getAllBrand(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "id") String filed,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int limit
    ) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.findAllShow(search, filed, sort, page, limit), HttpStatus.OK);
    }

    // lấy thông tin brand theo id
    @PostMapping("/{id}")
    public ResponseEntity<BrandRes> getById(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    // thêm brand mới
    @PostMapping("/addBrand")
    public ResponseEntity<BrandRes> addBrand(@Valid @ModelAttribute BrandReq brandReq) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.addBrand(brandReq), HttpStatus.CREATED);
    }

    // Chỉnh sửa thông tin brand
    @PostMapping("/update")
    public ResponseEntity<BrandRes> updateBrand(@Valid @ModelAttribute BrandReq brandReq) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.updateBrand(brandReq), HttpStatus.OK);
    }

    // xóa brand theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.delete(id), HttpStatus.OK);
    }
}
