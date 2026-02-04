package com.profession.suggest.controllers.pupil;

import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.database.services.pupil.subject.PupilGradeService;
import com.profession.suggest.database.services.pupil.subject.SubjectService;
import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileDTO;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pupil-subjects")
public class PupilSubjectsController {
    private final PupilGradeService pupilGradeService;
    private final PupilService pupilService;
    private final SubjectService subjectService;

    public PupilSubjectsController(PupilGradeService pupilGradeService, PupilService pupilService, SubjectService subjectService) {
        this.pupilGradeService = pupilGradeService;
        this.pupilService = pupilService;
        this.subjectService = subjectService;
    }

    @PostMapping("/add-info")
    public ResponseEntity<String> addGradesToPupil(@RequestAttribute("accountId") Long accountId, @RequestBody List<PupilSubjectDTO> pupilSubjectDTOS) {
        subjectService.addSubjectProfilesForPupil(pupilSubjectDTOS, pupilService.getPupilByAccountId(accountId));
        pupilGradeService.addGradesToPupil(accountId, pupilSubjectDTOS);
        return ResponseEntity.ok("Saved");
    }
    @GetMapping
    public ResponseEntity<List<PupilSubjectDTO>> getPupilSubjectWithGrades(@RequestAttribute("accountId") Long accountId) {
        return ResponseEntity.ok(pupilGradeService.getPupilSubject(accountId));
    }
    @PostMapping("/add-levels")
    public ResponseEntity<String> addLevelsToPupil(@RequestAttribute("accountId") Long accountId, @RequestBody PupilSubjectProfileDTO pupilSubjectProfileDTO) {
        try {
            //TODO
            return ResponseEntity.ok("Levels Saved");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
