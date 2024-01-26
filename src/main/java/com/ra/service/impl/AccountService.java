package com.ra.service.impl;

import com.ra.exception.NotEmptyCustomer;
import com.ra.mapper.AccountMapper;
import com.ra.mapper.ShopMapper;
import com.ra.model.dto.AccountDTO;
import com.ra.model.entity.Account;
import com.ra.model.entity.Shop;
import com.ra.repository.IAccountRepository;
import com.ra.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<AccountDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer {
        Sort sort1 = Sort.by(filed);
        Page<Account> accountPage = accountRepository.findAllBySearch(search, PageRequest.of(page, limit).withSort(sort1));
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountPage) {
            if (!account.isDeleteFlag()) {
                AccountDTO accountDTO = AccountMapper.INSTANCE.accountToAccountDTO(account);
                accountDTOList.add(accountDTO);
            }
        }
        return accountDTOList;
    }

    @Override
    public AccountDTO findById(Long id) throws NotEmptyCustomer {
        Optional<Account> a = accountRepository.findById(id);
        if (a.isPresent() && a.get().isDeleteFlag()) {
            return AccountMapper.INSTANCE.accountToAccountDTO(a.get());
        }
        throw new NotEmptyCustomer("Không tìm thấy shop");
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) throws NotEmptyCustomer {
        try {
            Account account = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
            Account saveAccount = accountRepository.save(account);
            return AccountMapper.INSTANCE.accountToAccountDTO(saveAccount);
        } catch (Exception e) {
            throw new RuntimeException("" + e.getMessage());
        }
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) throws NotEmptyCustomer {
        try {
            Long accountId = accountDTO.getId();
            if (accountId == null) {
                throw new NotEmptyCustomer("ID tài khoản không được rỗng ");
            }
            Optional<Account> existingAccountOptional = accountRepository.findById(accountId);
            if (existingAccountOptional.isPresent()) {
                Account existingAccount = existingAccountOptional.get();
                existingAccount.setLoginId(accountDTO.getLoginId());
                existingAccount.setPassWord(accountDTO.getPassWord());
                existingAccount.setRoleId(accountDTO.getRoleId());
                existingAccount.setAgentId(accountDTO.getAgentId());
                existingAccount.setOwnerId(accountDTO.getOwnerId());
                existingAccount.setShopId(accountDTO.getShopId());
                existingAccount.setActivatedFlag(accountDTO.isActivatedFlag());
                existingAccount.setDeleteFlag(accountDTO.isDeleteFlag());
                Account updateAccount = accountRepository.save(existingAccount);
                return AccountMapper.INSTANCE.accountToAccountDTO(updateAccount);
            } else {
                throw new NotEmptyCustomer("Tài khoản không được tìm thấy");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi cập nhật " + e.getMessage(), e);
        }
    }

    @Override
    public String delete(Long id) throws NotEmptyCustomer {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent() && account.get().isDeleteFlag()) {
            Account account1 = account.get();
            account1.setDeleteFlag(false);
            accountRepository.save(account1);
            return "Xóa thành công !";
        }
        throw new NotEmptyCustomer("Tài khoản không tồn tại");
    }
}
