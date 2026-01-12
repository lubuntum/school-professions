package com.profession.suggest.database.services.auth;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.repositories.auth.AccountRepository;
import com.profession.suggest.database.services.auth.role.RoleService;
import com.profession.suggest.dto.auth.AccountDTO;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import com.profession.suggest.services.jwt.JWTService;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public AccountService(AccountRepository repository, RoleService roleService, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public Account createAccount(Account account) {
        return repository.save(account);
    }
    public Boolean isEmailFree(String email) {
        return repository.findByEmail(email) == null;
    }
    public String login (AccountDTO accountDTO) throws AccountNotFoundException {
        Account account = repository.findByEmail(accountDTO.getEmail());
        if (account == null)
            throw new AccountNotFoundException("Email or password incorrect");
        if (!passwordEncoder.matches(accountDTO.getPassword(), account.getPassword()))
            throw new AccountNotFoundException("Email or password incorrect");

        return jwtService.generateToken(String.valueOf(account.getId()));
    }
    @Transactional
    public String registration(AccountRegisterRequestDTO accountRegisterRequestDTO) throws BadRequestException {
        if (accountRegisterRequestDTO == null || accountRegisterRequestDTO.getEmail() == null
                || accountRegisterRequestDTO.getPassword() == null)
            throw new BadRequestException("Fill all fields");
        if (!isEmailFree(accountRegisterRequestDTO.getEmail()))
            throw new IllegalArgumentException("Email already in use");
        Account account = new Account();
        account.setEmail(accountRegisterRequestDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountRegisterRequestDTO.getPassword()));

        Role role = roleService.findByName(RoleEnum.PUPIL);
        if (account.getRoles() == null)
            account.setRoles(new HashSet<>());
        account.getRoles().add(role);
        return repository.save(account).getEmail();
    }
    public PupilResponseDTO getPupilDataByAccountId(Long id) {
        return repository.findPupilDataByAccountId(id);
    }
    public Account getAccountById(Long id) throws AccountNotFoundException {
        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException("ss"));
    }
}
