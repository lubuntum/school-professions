package com.profession.suggest.controllers.pupil;

import com.profession.suggest.database.services.pupil.subject.PupilGradeService;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pupil-subjects")
public class PupilSubjectsController {
    private final PupilGradeService pupilGradeService;

    public PupilSubjectsController(PupilGradeService pupilGradeService) {
        this.pupilGradeService = pupilGradeService;
    }

    @PostMapping("/add-grades")
    public ResponseEntity<String> addGradesToPupil(@RequestAttribute("accountId") Long accountId, @RequestBody List<PupilSubjectDTO> pupilSubjectDTOS) {
        pupilGradeService.addGradesToPupil(accountId, pupilSubjectDTOS);
        return ResponseEntity.ok("Saved");
    }
    @GetMapping
    public ResponseEntity<List<PupilSubjectDTO>> getPupilSubjectWithGrades(@RequestAttribute("accountId") Long accountId) {
        return ResponseEntity.ok(pupilGradeService.getPupilSubject(accountId));
    }
}
