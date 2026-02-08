package com.profession.suggest.database.services.pupil.subject;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.PupilGrade;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.repositories.pupil.subject.PupilGradeRepository;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.pupil.subject.PupilGradeDTO;
import com.profession.suggest.dto.pupil.subject.PupilGradeMapper;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import com.profession.suggest.dto.pupil.subject.PupilSubjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PupilGradeService {
    private final PupilGradeRepository repository;
    private final PupilService pupilService;
    private final SubjectService subjectService;
    private final PupilGradeMapper pupilGradeMapper;
    private final PupilSubjectMapper pupilSubjectMapper;

    public PupilGradeService(PupilGradeRepository repository, PupilService pupilService, SubjectService subjectService, PupilGradeMapper pupilGradeMapper, PupilSubjectMapper pupilSubjectMapper) {
        this.repository = repository;
        this.pupilService = pupilService;
        this.subjectService = subjectService;
        this.pupilGradeMapper = pupilGradeMapper;
        this.pupilSubjectMapper = pupilSubjectMapper;
    }
    @Transactional
    public void addGradesToPupil(Long accountId, List<PupilSubjectDTO> pupilSubjectDTOS) {
        if (pupilSubjectDTOS == null || pupilSubjectDTOS.isEmpty()) {
            throw new RuntimeException("List of subjects for pupil is empty ");
        }
        Pupil pupil = pupilService.getPupilByAccountId(accountId);
        for(PupilSubjectDTO pupilSubjectDTO: pupilSubjectDTOS) {
            Subject subject = subjectService.getSubjectByName(pupilSubjectDTO.getName());
            for(PupilGradeDTO pupilGradeDTO: pupilSubjectDTO.getGrades()) {
                Optional<PupilGrade> existingGrade = repository.findByPupilAndSubjectAndClassNumber(pupil, subject, pupilGradeDTO.getClassNumber());
                if (existingGrade.isPresent()) {
                    PupilGrade pupilGrade = existingGrade.get();
                    pupilGrade.setGrade(pupilGradeDTO.getGrade());
                    repository.save(pupilGrade);
                    continue;
                }
                PupilGrade pupilGrade = pupilGradeMapper.fromDTO(pupilGradeDTO);
                pupilGrade.setSubject(subject);
                pupilGrade.setPupil(pupil);
                repository.save(pupilGrade);
            }
        }
    }
    public List<PupilSubjectDTO> getPupilSubject(Pupil pupil) {

        List<PupilGrade> pupilGrades = repository.findByPupil(pupil);
        //get all subjects for split grades between them for parsing to DTOs
        List<Subject> subjects = subjectService.getAllSubjects();
        List<PupilSubjectDTO> result = new ArrayList<>();
        return subjects.stream()
                .map(subject -> pupilSubjectMapper.toDTO(pupilGrades, subject))
                .collect(Collectors.toList());
    }
}
