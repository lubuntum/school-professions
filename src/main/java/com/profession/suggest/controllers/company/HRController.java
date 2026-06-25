package com.profession.suggest.controllers.company;

import com.profession.suggest.configuration.security.annotation.HasRole;
import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.specialist.CompanyService;
import com.profession.suggest.dto.company.CreateHRRequest;
import com.profession.suggest.dto.company.HRResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hr")
public class HRController {
    private final CompanyService companyService;
    @HasRole(RoleEnum.ADMIN)
    @GetMapping
    public ResponseEntity<?> getAllHRs() {
        try {
            return ResponseEntity.ok(companyService.getHRsWithCompanies());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @HasRole(RoleEnum.ADMIN)
    @PutMapping
    public ResponseEntity<?> createHR(@RequestBody CreateHRRequest request) {
        try {
            return ResponseEntity.ok(companyService.createHRWithCompany(request));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
