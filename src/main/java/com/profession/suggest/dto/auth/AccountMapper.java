package com.profession.suggest.dto.auth;

import com.profession.suggest.database.entities.auth.Account;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account fromDTO(AccountRegisterRequestDTO dto) {
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }
}
