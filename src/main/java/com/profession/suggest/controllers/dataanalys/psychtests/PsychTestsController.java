package com.profession.suggest.controllers.dataanalys.psychtests;

import com.profession.suggest.database.services.dataanalys.psychtests.PsychParamService;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychTestService;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psych-tests")
public class PsychTestsController {
    private final PsychTestService psychTestService;
    //private final PsychTestTypeService psychTestTypeService; admin etc
    private final PsychParamService psychParamService;
    //private final PsychParamNameService psychParamNameService; admin etc
    private final PupilService pupilService;
    public PsychTestsController(PsychTestService psychTestService, PsychParamService psychParamService, PupilService pupilService) {
        this.psychTestService = psychTestService;
        this.psychParamService = psychParamService;
        this.pupilService = pupilService;
    }

    @PostMapping("/create-test")
    public ResponseEntity<PsychTestDTO> createPsychTestForPupil(@RequestBody PsychTestDTO requestDTO, @RequestAttribute("accountId") Long accountId) {
        return ResponseEntity.ok(
                psychTestService.createPsychTestForPupil(
                        requestDTO,
                        pupilService.getPupilByAccountId(accountId)));
    }
    @GetMapping("/my-tests")
    public ResponseEntity<List<PsychTestDTO>> getTestsForPupil(@RequestAttribute("accountId") Long accountId) {
        return ResponseEntity.ok(psychTestService.getPupilTests(pupilService.getPupilByAccountId(accountId)));
    }
    @GetMapping("/my-tests/type/{testType}")
    public ResponseEntity<List<PsychTestDTO>> getTestsByType(@RequestAttribute("accountId") Long accountId, @PathVariable String testType) {
        return ResponseEntity.ok(psychTestService.getPupilTestByType(pupilService.getPupilByAccountId(accountId), testType));
    }


}
