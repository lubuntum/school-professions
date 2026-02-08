package com.profession.suggest.database.services.pupil.subject;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.services.pupil.subject.profile.PupilSubjectProfileService;
import com.profession.suggest.dto.pupil.subject.PupilGradeDTO;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectInfoService {
    private final PupilGradeService pupilGradeService;
    private final PupilSubjectProfileService pupilSubjectProfileService;
    private final SubjectService subjectService;

    public SubjectInfoService(PupilGradeService pupilGradeService, PupilSubjectProfileService pupilSubjectProfileService, SubjectService subjectService) {
        this.pupilGradeService = pupilGradeService;
        this.pupilSubjectProfileService = pupilSubjectProfileService;
        this.subjectService = subjectService;
    }

    public List<PupilSubjectDTO> getPupilSubjectsInfo(Pupil pupil) {
        List<PupilSubjectDTO> pupilSubjectDTOS = pupilGradeService.getPupilSubject(pupil);
        List<String> subjectNames = pupilSubjectDTOS.stream()
                .map(PupilSubjectDTO::getName)
                .toList();
        List<Subject> subjects = subjectService.getSubjectsByNames(subjectNames);

        Map<String, List<PupilGradeDTO>> gradesBySubject = pupilSubjectDTOS.stream()
                .collect(Collectors.toMap(
                        PupilSubjectDTO::getName,
                        PupilSubjectDTO::getGrades
                ));
        return subjects.stream()
                .map(subject -> {
                    PupilSubjectDTO dto = new PupilSubjectDTO();
                    dto.setName(subject.getName());
                    // Get grades from the map (could be null)
                    dto.setGrades(gradesBySubject.get(subject.getName()));
                    // Get profile for this subject
                    dto.setPupilSubjectProfileDTO(pupilSubjectProfileService
                            .getProfileBySubjectAndPupil(pupil, subject));
                    return dto;
                })
                .collect(Collectors.toList());

    }
}
