package com.ra.service;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.AccountDTO;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer;
    AccountDTO findById(Long id) throws NotEmptyCustomer;
    AccountDTO addAccount(AccountDTO accountDTO) throws NotEmptyCustomer;
    AccountDTO updateAccount(AccountDTO accountDTO) throws NotEmptyCustomer;
    String delete(Long id) throws NotEmptyCustomer;
}
