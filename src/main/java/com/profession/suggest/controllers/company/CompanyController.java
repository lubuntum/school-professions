package com.profession.suggest.controllers.company;

import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.specialist.CompanyService;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/company")
public class CompanyController {
    private final AccountService accountService;
    private final CompanyService companyService;

    @GetMapping()
    public ResponseEntity<?> getMyCompany(@RequestAttribute("accountId") Long accountId) {
        try {
            return ResponseEntity.ok(companyService.getCompanyByAccountId(accountId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error getting company: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    /**
     * Return all connected specialists by account's company
     * */
    @GetMapping("/specialists")
    public ResponseEntity<?> getSpecialistsByCompany (@RequestAttribute("accountId") Long accountId) {
        try {
            List<SpecialistDTO> specialists = companyService.getSpecialistDTOsByHRAccountId(accountId);
            return ResponseEntity.ok(specialists);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error getting company specialists: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


}
