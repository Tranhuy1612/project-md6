package com.ra.service;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.request.BrandReq;
import com.ra.model.dto.response.BrandRes;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface IBrandService {
    List<BrandRes> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer;

    BrandRes findById(Long id) throws NotEmptyCustomer;

    BrandRes addBrand(BrandReq brandReq) ;
    BrandRes updateBrand(BrandReq brandReq) throws NotEmptyCustomer;
    String delete(Long id)throws NotEmptyCustomer;
}
