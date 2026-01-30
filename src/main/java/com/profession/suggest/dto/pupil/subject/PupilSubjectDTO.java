package com.profession.suggest.dto.pupil.subject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PupilSubjectDTO {
    private String name;
    private List<PupilGradeDTO> grades;
}
