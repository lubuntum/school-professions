package com.profession.suggest.dto.pupil.subject;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class PupilGradeDTO {
    private Integer classNumber;
    private String grade;
}
