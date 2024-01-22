package com.ra.service.impl;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.request.BrandReq;
import com.ra.model.dto.response.BrandRes;
import com.ra.model.entity.Brand;
import com.ra.repository.IBrandRepository;
import com.ra.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Value("${path-upload}")
    private String pathUpload;
    @Value("${server.port}")
    private Long port;
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<BrandRes> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer {
        Sort sort1 = Sort.by(filed);
        Page<Brand> brands = brandRepository.findAllBySearch(search, PageRequest.of(page, limit).withSort(sort1));
        List<BrandRes> brandResList = new ArrayList<>();
        for (Brand b : brands) {
            if (b.isDeleteFlag()) {
                BrandRes brandRes = BrandRes.builder()
                        .id(b.getId())
                        .brandName(b.getBrandName())
                        .brandLogo(b.getBrandLogo())
                        .brandUrl(b.getBrandUrl())
                        .miniFlyer(b.getMiniFlyer())
                        .storeFlyer(b.getStoreFlyer())
                        .createDate(b.getCreateDate())
                        .usageFlag(b.isUsageFlag()).build();

                brandResList.add(brandRes);
            }
        }
        return brandResList;
    }

    @Override
    public BrandRes findById(Long id) throws NotEmptyCustomer {
        Optional<Brand> b = brandRepository.findById(id);
        if (b.isPresent() && b.get().isDeleteFlag()) {
            return BrandRes.builder()
                    .id(b.get().getId())
                    .brandName(b.get().getBrandName())
                    .brandLogo(b.get().getBrandLogo())
                    .brandUrl(b.get().getBrandUrl())
                    .miniFlyer(b.get().getMiniFlyer())
                    .storeFlyer(b.get().getStoreFlyer())
                    .createDate(b.get().getCreateDate())
                    .usageFlag(b.get().isUsageFlag()).build();
        }
        throw new NotEmptyCustomer("Không tìm thấy thương hiệu");
    }

    @Override
    public BrandRes addBrand(BrandReq brandReq) {
        MultipartFile brandLogo = brandReq.getBrandLogo();
        if (brandLogo != null && !brandLogo.isEmpty()) {
            String fileName = brandReq.getBrandLogo().getOriginalFilename();

            try {
                FileCopyUtils.copy(brandReq.getBrandLogo().getBytes(), new File(pathUpload + fileName));
                Brand brand = Brand.builder()
                        .brandName(brandReq.getBrandName())
                        .brandUrl(brandReq.getBrandUrl())
                        .brandLogo("http://localhost:" + port + "/" + fileName)
                        .usageFlag(true)
                        .miniFlyer(brandReq.getMiniFlyer())
                        .storeFlyer(brandReq.getStoreFlyer()).build();
                Brand b = brandRepository.save(brand);
                return BrandRes.builder()
                        .id(b.getId())
                        .brandUrl(b.getBrandUrl())
                        .brandName(b.getBrandName())
                        .brandLogo(b.getBrandLogo())
                        .storeFlyer(b.getStoreFlyer())
                        .miniFlyer(b.getMiniFlyer())
                        .usageFlag(b.isUsageFlag()).build();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("Logo thương hiệu là bắt buộc");
        }
    }

    @Override
    public BrandRes updateBrand(BrandReq brandReq) throws NotEmptyCustomer {
        String fileName = brandReq.getBrandLogo().getOriginalFilename();
        try {
            FileCopyUtils.copy(brandReq.getBrandLogo().getBytes(), new File(pathUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        Optional<Brand> brand = brandRepository.findById(brandReq.getId());
        if (brand.isPresent() && brand.get().isDeleteFlag()) {
            Brand brand1 = brand.get();
            brand1.setBrandUrl(brandReq.getBrandUrl());
            brand1.setBrandName(brandReq.getBrandName());
            brand1.setBrandLogo("http://localhost:" + port + "/" + fileName);
            brand1.setStoreFlyer(brand1.getStoreFlyer());
            brand1.setMiniFlyer(brand1.getMiniFlyer());
            brand1.setUsageFlag(brand1.isUsageFlag());
            Brand b = brandRepository.save(brand1);
            return BrandRes.builder()
                    .id(b.getId())
                    .brandName(b.getBrandName())
                    .brandUrl(b.getBrandUrl())
                    .brandLogo(b.getBrandLogo())
                    .storeFlyer(b.getStoreFlyer())
                    .miniFlyer(b.getMiniFlyer())
                    .usageFlag(b.isUsageFlag()).build();
        }
        throw new NotEmptyCustomer("hãng không tồn tại");
    }

    @Override
    public String delete(Long id) throws NotEmptyCustomer {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent() && brand.get().isDeleteFlag()) {
            Brand brand1 = brand.get();
            brand1.setDeleteFlag(false);
            brandRepository.save(brand1);
            return "Xóa thành công !";
        }
        throw new NotEmptyCustomer("sản phẩm không tồn tại ");
    }

}
