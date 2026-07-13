package com.profession.suggest.controllers.company;

import com.profession.suggest.configuration.security.annotation.HasRole;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.services.specialist.CompanyService;
import com.profession.suggest.dto.company.AllowedRole;
import com.profession.suggest.dto.company.CreateEmployeeRequest;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hr")
public class HRController {
    private final CompanyService companyService;
    /**
     * Admin creates HR with company
     */
    @HasRole(RoleEnum.ADMIN)
    @PostMapping
    public ResponseEntity<?> createHR(@RequestBody CreateEmployeeRequest request) {
        try {
            // Force role to HR for this endpoint
            request.setRole(AllowedRole.HR);
            var response = companyService.createEmployeeForCompany(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);  // ? 201 CREATED
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, please check request");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error, please check request params");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Probably OGRN, INN or name duplicate error");
        }
    }
    /**
     * HR creates Specialist (regular employee) in their company
     */
    @HasRole({RoleEnum.HR, RoleEnum.ADMIN})
    @PostMapping("/specialists")
    public ResponseEntity<?> createSpecialist(
            @RequestAttribute("accountId") Long accountId,
            @RequestBody CreateEmployeeRequest request) {
        try {
            // Force role to SPECIALIST (HR cannot create another HR)
            request.setRole(AllowedRole.SPECIALIST);

            // Optional: If companyName is not provided, use HR's company
            if (request.getCompanyName() == null || request.getCompanyName().isEmpty()) {
                var company = companyService.getCompanyByAccountId(accountId);
                request.setCompanyName(company.getName());
            }
            var response = companyService.createEmployeeForCompany(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
