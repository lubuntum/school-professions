package com.profession.suggest.controllers.specialist;

import com.profession.suggest.database.entities.specialist.Specialist;
import com.profession.suggest.dto.specialist.SpecialistRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialists")
public class SpecialistController {
    @PostMapping("/register-all")
    public ResponseEntity<?> autoRegister(@RequestBody List<SpecialistRegisterRequest> specialistRegisterRequest) {
        return null;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SpecialistRegisterRequest specialistRegisterRequest) {
        return null;
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateSpecialist(@RequestBody Specialist specialist) {
        return null;
    }
}
