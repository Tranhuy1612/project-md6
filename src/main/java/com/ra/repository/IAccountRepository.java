package com.ra.repository;

import com.ra.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT a FROM Account a WHERE LOWER(a.loginId) LIKE %:search% ")
    Page<Account> findAllBySearch(@Param("search") String search, Pageable pageable);
}
