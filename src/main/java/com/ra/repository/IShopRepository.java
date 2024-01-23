package com.ra.repository;

import com.ra.model.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopRepository extends JpaRepository<Shop, Long> {
    @Query(value = "SELECT s FROM Shop s WHERE LOWER(s.ownerName) LIKE %:search% " +
            "OR LOWER(s.companyName) LIKE %:search% " + "OR LOWER(s.ownerPhone) LIKE %:search%"
    )
    Page<Shop> findAllBySearch(@Param("search") String search, Pageable pageable);
}
