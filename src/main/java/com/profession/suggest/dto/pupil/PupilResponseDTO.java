package com.profession.suggest.dto.pupil;

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
                            String nationality, String extraActivities, String email) {
        this.pupilDTO = new PupilDTO(name, surname, patronymic, birthday, school, healthCondition, nationality, extraActivities);
        this.email = email;
    }
}
