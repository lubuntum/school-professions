package com.profession.suggest.dto.pupil.subject;

import com.profession.suggest.database.entities.pupil.subject.PupilGrade;
import org.springframework.stereotype.Component;

@Component
public class PupilGradeMapper {
    public PupilGrade fromDTO(PupilGradeDTO dto) {
        PupilGrade pupilGrade = new PupilGrade();
        pupilGrade.setGrade(dto.getGrade());
        pupilGrade.setClassNumber(dto.getClassNumber());
        return  pupilGrade;
    }
    public PupilGradeDTO toDTO(PupilGrade pupilGrade) {
        PupilGradeDTO dto = new PupilGradeDTO();
        dto.setGrade(pupilGrade.getGrade());
        dto.setClassNumber(pupilGrade.getClassNumber());
        return dto;
    }
}
