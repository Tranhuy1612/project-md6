package com.ra.service.impl;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.AccountDTO;
import com.ra.model.entity.Account;
import com.ra.repository.IAccountRepository;
import com.ra.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<AccountDTO> findAllShow(String search, String filed, String sort, Integer page, Integer limit) throws NotEmptyCustomer {
        return null;
    }

    @Override
    public AccountDTO findById(Long id) throws NotEmptyCustomer {
        return null;
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) throws NotEmptyCustomer {
        return null;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) throws NotEmptyCustomer {
        return null;
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
