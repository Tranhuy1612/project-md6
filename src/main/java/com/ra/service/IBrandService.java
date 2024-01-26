package com.ra.service;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.BrandDTO;
import com.ra.model.entity.Brand;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public interface IBrandService {
    List<BrandDTO> findAllShow(String brandName, String url,Integer startId, Integer endId, Integer page, Integer limit) ;

    BrandDTO findById(Long id) throws NotEmptyCustomer;

    BrandDTO addBrand(BrandDTO brandDTO) throws IOException;
    BrandDTO updateBrand(BrandDTO brandDTO) throws NotEmptyCustomer;
    String delete(Long id)throws NotEmptyCustomer;

    //    File CSV
    void exportToCsv(HttpServletResponse response, List<Brand> data) throws IOException;
}
