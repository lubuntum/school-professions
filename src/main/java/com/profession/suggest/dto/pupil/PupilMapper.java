package com.profession.suggest.dto.pupil;

import com.profession.suggest.database.entities.pupil.Pupil;
import org.springframework.stereotype.Component;

@Component
public class PupilMapper {
    public Pupil fromDTO(PupilDTO dto) {
        Pupil pupil = new Pupil();
        pupil.setName(dto.getName());
        pupil.setSurname(dto.getSurname());
        pupil.setPatronymic(dto.getPatronymic());
        pupil.setBirthday(dto.getBirthday());
        pupil.setSchool(dto.getSchool());
        pupil.setHealthCondition(dto.getHealthCondition());
        pupil.setNationality(dto.getNationality());
        pupil.setExtraActivities(dto.getExtraActivities());
        pupil.setClassNumber(dto.getClassNumber());
        pupil.setClassLabel(dto.getClassLabel());
        return pupil;
    }
    public PupilDTO toDTO(Pupil pupil) {
        PupilDTO dto = new PupilDTO();
        dto.setId(pupil.getId());
        dto.setName(pupil.getName());
        dto.setSurname(pupil.getSurname());
        dto.setPatronymic(pupil.getPatronymic());
        dto.setBirthday(pupil.getBirthday());
        dto.setSchool(pupil.getSchool());
        dto.setHealthCondition(pupil.getHealthCondition());
        dto.setNationality(pupil.getNationality());
        dto.setExtraActivities(pupil.getExtraActivities());
        dto.setClassNumber(pupil.getClassNumber());
        dto.setClassLabel(pupil.getClassLabel());
        dto.setGender(pupil.getGender().getName());
        return dto;
    }
}
