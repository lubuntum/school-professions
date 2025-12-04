package com.profession.suggest.dto.pupil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PupilDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    @Size(max = 50, message = "Surname cannot exceed 50 characters")
    private String surname;

    @Size(max = 50, message = "Patronymic cannot exceed 50 characters")
    private String patronymic;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    @Size(max = 100, message = "School name cannot exceed 100 characters")
    private String school;

    @Size(max = 500, message = "Health condition notes are too long")
    private String healthCondition;

    @Size(max = 50, message = "Nationality cannot exceed 50 characters")
    private String nationality;

    @Size(max = 500, message = "Extra activities description is too long")
    private String extraActivities;
}
