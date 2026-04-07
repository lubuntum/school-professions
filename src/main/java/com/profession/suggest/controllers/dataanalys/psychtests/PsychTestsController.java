package com.profession.suggest.controllers.dataanalys.psychtests;

import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychTestService;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/psych-tests")
public class PsychTestsController {
    private final PsychTestService psychTestService;
    //private final PsychTestTypeService psychTestTypeService; admin etc
    private final AccountService accountService;
    public PsychTestsController(PsychTestService psychTestService, AccountService accountService) {
        this.psychTestService = psychTestService;
        this.accountService = accountService;
    }
    //TODO create test for pupil and specialist
    @PostMapping("/create-test")
    public ResponseEntity<PsychTestDTO> createPsychTest(@RequestBody PsychTestDTO requestDTO, @RequestAttribute("accountId") Long accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(
                psychTestService.createPsychTest(
                        requestDTO,
                        accountService.getAccountById(accountId)));
    }
    @GetMapping("/my-tests")
    public ResponseEntity<List<PsychTestDTO>> getTestsForAccount(@RequestAttribute("accountId") Long accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(psychTestService.getTestsResultsByAccount(accountService.getAccountById(accountId)));
    }
    @GetMapping("/my-recent-tests")
    public ResponseEntity<Map<String, PsychTestDTO>> getAccountRecentTests(@RequestAttribute("accountId") Long accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(psychTestService.getAccountRecentTests(accountService.getAccountById(accountId)));
    }
    @GetMapping("/my-tests/type/{testType}")
    public ResponseEntity<List<PsychTestDTO>> getTestsByType(@RequestAttribute("accountId") Long accountId, @PathVariable String testType) throws AccountNotFoundException {
        return ResponseEntity.ok(psychTestService.getAccountTestsByType(accountService.getAccountById(accountId), testType));
    }


}
