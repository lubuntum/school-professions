package com.profession.suggest.controllers.auth;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.pupil.PupilDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PupilService pupilService;

    public AuthController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @PostMapping("/auto-register")
    public ResponseEntity<PupilDTO> autoRegister(@Valid @RequestBody AccountApiRegisterDTO accountApiRegisterDTO) {
        return ResponseEntity.ok(pupilService.createWithAccount(accountApiRegisterDTO));
    }

}
