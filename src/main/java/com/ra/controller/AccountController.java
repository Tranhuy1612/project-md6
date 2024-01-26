package com.ra.controller;

import com.ra.exception.NotEmptyCustomer;
import com.ra.model.dto.AccountDTO;
import com.ra.model.dto.ShopDTO;
import com.ra.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllAccount(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "id") String filed,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int limit
    ) throws NotEmptyCustomer {
        return new ResponseEntity<>(accountService.findAllShow(search, filed, sort, page, limit), HttpStatus.OK);
    }

    //lấy thông tin tài khoản theo id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    // thêm mới account
    @PostMapping("/addAccount")
    public ResponseEntity<AccountDTO> addAccount(@ModelAttribute AccountDTO accountDTO) throws NotEmptyCustomer {
        return new ResponseEntity<>(accountService.addAccount(accountDTO), HttpStatus.CREATED);
    }

    // Chỉnh sửa tài khoản theo Id
    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@ModelAttribute AccountDTO accountDTO) throws NotEmptyCustomer {
        return new ResponseEntity<>(accountService.updateAccount(accountDTO), HttpStatus.OK);
    }

    // xóa tài khoản theo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) throws NotEmptyCustomer {
        return new ResponseEntity<>(accountService.delete(id), HttpStatus.OK);
    }
}
