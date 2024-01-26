package com.ra.mapper;

import com.ra.model.dto.AccountDTO;
import com.ra.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDTO accountToAccountDTO(Account account);
    Account accountDTOToAccount(AccountDTO accountDTO);
}
