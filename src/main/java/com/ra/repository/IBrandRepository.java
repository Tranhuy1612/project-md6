package com.ra.repository;

import com.ra.model.dto.BrandDTO;
import com.ra.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "SELECT b FROM Brand b WHERE LOWER(b.brandName) LIKE %:search% OR LOWER(b.brandUrl) LIKE %:search%")
    Page<Brand> findAllBySearch(@Param("search") String search, Pageable pageable);
}
