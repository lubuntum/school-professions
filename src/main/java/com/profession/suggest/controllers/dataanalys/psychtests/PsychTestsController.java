package com.profession.suggest.controllers.dataanalys.psychtests;

import com.profession.suggest.database.services.dataanalys.psychtests.PsychParamNameService;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychParamService;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychTestService;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychTestTypeService;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestPupilRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/psych-tests")
public class PsychTestsController {
    private final PsychTestService psychTestService;
    //private final PsychTestTypeService psychTestTypeService; admin etc
    private final PsychParamService psychParamService;
    //private final PsychParamNameService psychParamNameService; admin etc
    public PsychTestsController(PsychTestService psychTestService, PsychParamService psychParamService) {
        this.psychTestService = psychTestService;
        this.psychParamService = psychParamService;
    }

    @PostMapping("/create-test")
    public ResponseEntity<String> createPsychTestForPupil(@RequestBody PsychTestPupilRequestDTO requestDTO) {
        psychTestService.createPsychTestForPupil(requestDTO);
        return ResponseEntity.ok("Test created");
    }

}
