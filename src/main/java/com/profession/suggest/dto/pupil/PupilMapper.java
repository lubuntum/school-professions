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
        return pupil;
    }
}
