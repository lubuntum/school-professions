package com.profession.suggest.controllers.auth;

import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.auth.AccountDTO;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.services.jwt.JWTService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> register(@RequestBody AccountRegisterRequestDTO account) {
        return ResponseEntity.ok(accountService.registration(account));
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountDTO account) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.login(account));
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
