package com.profession.suggest.database.services.auth;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.repositories.auth.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }
    public void createAccount(Account account) {
        repository.save(account);
    }
}
