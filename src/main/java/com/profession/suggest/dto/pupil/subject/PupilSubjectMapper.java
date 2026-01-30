package com.profession.suggest.dto.pupil.subject;

import com.profession.suggest.database.entities.pupil.subject.PupilGrade;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PupilSubjectMapper {
    private final PupilGradeMapper pupilGradeMapper;

    public PupilSubjectMapper(PupilGradeMapper pupilGradeMapper) {
        this.pupilGradeMapper = pupilGradeMapper;
    }

    public PupilSubjectDTO toDTO (List<PupilGrade> pupilGrades, Subject subject) {
        PupilSubjectDTO dto = new PupilSubjectDTO();
        dto.setName(subject.getName());
        dto.setGrades(pupilGrades.stream()
                .filter(grade -> Objects.equals(grade.getSubject().getName(), subject.getName()))
                .map(pupilGradeMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
