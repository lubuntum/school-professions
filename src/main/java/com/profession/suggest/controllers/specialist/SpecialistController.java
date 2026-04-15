package com.profession.suggest.controllers.specialist;

import com.profession.suggest.configuration.security.annotation.HasRole;
import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.profession.ProfessionService;
import com.profession.suggest.database.services.specialist.SpecialistService;
import com.profession.suggest.dto.specialist.ProfessionDTO;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import com.profession.suggest.dto.specialist.SpecialistMapper;
import com.profession.suggest.dto.specialist.SpecialistRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
//TODO add some specialists and check if all transfer good
// - start working for auto creating a bunch of specialists from xlsx client side
@RestController
@RequestMapping("/api/specialists")
@Slf4j
public class SpecialistController {
    private final SpecialistService specialistService;
    public final ProfessionService professionService;
    public final AccountService accountService;

    public SpecialistController(SpecialistService specialistService, ProfessionService professionService, AccountService accountService, SpecialistMapper specialistMapper) {
        this.specialistService = specialistService;
        this.professionService = professionService;
        this.accountService = accountService;
    }
    @HasRole(RoleEnum.ADMIN)
    @PostMapping("/register-all")
    public ResponseEntity<String> autoRegister(@RequestBody List<SpecialistRegisterRequest> specialistRegisterRequest) {
        try {
            specialistService.createAllWithAccounts(specialistRegisterRequest);
            return ResponseEntity.ok("Specialists saved");
        } catch (Exception e) {
            log.error("Error occurred while saving specialists", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    //TODO test it
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SpecialistRegisterRequest specialistRegisterRequest) throws BadRequestException {
        try {
            Account account = accountService.registration(specialistRegisterRequest.getAccount(), RoleEnum.SPECIALIST);
            specialistService.create(specialistRegisterRequest.getSpecialist(), account);
            return ResponseEntity.ok(account.getEmail());
        } catch (Exception e) {
            log.error("Cannot save specialist", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @PutMapping("/specialist/{id}")
    public ResponseEntity<SpecialistDTO> updateSpecialist(@RequestBody SpecialistDTO specialistDTO, @PathVariable("id") Long id) {
        specialistDTO.setId(id);
        return ResponseEntity.ok(specialistService.update(specialistDTO));
    }
    //TODO there is a lot space for filter fields if need.
    @GetMapping()
    public ResponseEntity<Page<SpecialistDTO>> getSpecialistsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by(
                        sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                        sortBy));
        try {
            return ResponseEntity.ok(specialistService.getSpecialistsPage(pageable));
        } catch (Exception e) {
            log.error("Error while getting specialists", e);
            throw new RuntimeException("Failed to get specialists");
        }
    }
    @GetMapping("/professions")
    public ResponseEntity<List<ProfessionDTO>> getProfessions() {
        return ResponseEntity.ok(professionService.getProfessions());
    }
    @PostMapping("/professions")
    public ResponseEntity<ProfessionDTO> createProfession(@RequestBody ProfessionDTO professionDTO) {
        return ResponseEntity.ok(professionService.createProfession(professionDTO));
    }
    @GetMapping("/specialist")
    public ResponseEntity<SpecialistDTO> getSpecialist(@RequestAttribute("accountId") Long accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(
                specialistService.getSpecialistByAccount(accountService.getAccountById(accountId)));
    }
}
