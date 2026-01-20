package com.profession.suggest.controllers.auth;

import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.auth.AccountDTO;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.auth.RoleDTO;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.services.jwt.JWTService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PupilService pupilService;
    private final AccountService accountService;
    private final JWTService jwtService;

    public AuthController(PupilService pupilService, AccountService accountService, JWTService jwtService) {
        this.pupilService = pupilService;
        this.accountService = accountService;
        this.jwtService = jwtService;
    }

    @PostMapping("/auto-register")
    public ResponseEntity<PupilDTO> autoRegister(@Valid @RequestBody AccountApiRegisterDTO accountApiRegisterDTO) {
        return ResponseEntity.ok(pupilService.createWithAccount(accountApiRegisterDTO));
    }
    @PostMapping("/auto-register-all")
    public ResponseEntity<String> autoRegisterAll(@RequestBody List<AccountApiRegisterDTO> accountApiRegisterDTOList) {
        pupilService.createAllWithAccount(accountApiRegisterDTOList);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountRegisterRequestDTO account) throws BadRequestException {
        try {
            return ResponseEntity.ok(accountService.registration(account));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please check all fields");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email already in use");
        }

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountDTO account) {
        try {
            return ResponseEntity.ok(accountService.login(account));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("please check login or password");
        }

    }
    @GetMapping("/account-roles")
    public ResponseEntity<List<RoleDTO>> getAccountRoles(@RequestAttribute("accountId") Long accountId) throws AccountNotFoundException {
        try {
            return ResponseEntity.ok(accountService.getRolesByAccount(accountId));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    @PostMapping("/protected-test")
    public ResponseEntity<String> testProtectedRoute(){
        return ResponseEntity.ok("This is protected route for testing jwt");
    }
    @GetMapping("/get-test-token")
    public ResponseEntity<String> getTestToken() {
        return ResponseEntity.ok(jwtService.generateToken("Hello user"));
    }

}
