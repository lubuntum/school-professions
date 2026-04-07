package com.profession.suggest.dto.specialist;

import com.profession.suggest.database.entities.professions.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialistDTO {
    private String name;
    private String surname;
    private String patronymic;
    private String contactEmail;
    private String contactPhone;
    private String experience;
    private String jobSatisfaction;
    private Profession profession;
}
