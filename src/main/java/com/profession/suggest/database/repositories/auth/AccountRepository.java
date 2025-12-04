package com.profession.suggest.database.repositories.auth;

import com.profession.suggest.database.entities.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
