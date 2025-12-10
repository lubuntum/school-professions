package com.profession.suggest.dto.pupil;

import com.profession.suggest.database.entities.gender.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PupilResponseDTO {
    public PupilDTO pupilDTO;
    public String email;
    public PupilResponseDTO(String name, String surname, String patronymic,
                            LocalDate birthday, String school, String healthCondition,
                            String nationality, String extraActivities, String className, GenderEnum gender, String email) {
        this.pupilDTO = new PupilDTO(name, surname, patronymic, birthday, school, healthCondition, nationality, extraActivities, className, gender);
        this.email = email;
    }
}
