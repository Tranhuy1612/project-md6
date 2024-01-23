package com.ra.controller;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.BrandDTO;
import com.ra.model.entity.Brand;
import com.ra.service.impl.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<BrandDTO>> getAllBrand(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "id") String filed,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int limit
    ) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.findAllShow(search, filed, sort, page, limit), HttpStatus.OK);
    }

    // lấy thông tin brand theo id
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getById(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    // thêm brand mới
    @PostMapping("/addBrand")
    public ResponseEntity<BrandDTO> addBrand(@Valid @RequestBody BrandDTO brandDTO) throws IOException {
        return new ResponseEntity<>(brandService.addBrand(brandDTO), HttpStatus.CREATED);
    }

    // Chỉnh sửa thông tin brand
    @PutMapping("/update")
    public ResponseEntity<BrandDTO> updateBrand(@Valid @RequestBody BrandDTO brandDTO) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.updateBrand(brandDTO), HttpStatus.OK);
    }

    // xóa brand theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(brandService.delete(id), HttpStatus.OK);
    }
}
