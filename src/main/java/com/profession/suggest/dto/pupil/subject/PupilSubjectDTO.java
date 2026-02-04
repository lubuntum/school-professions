package com.profession.suggest.dto.pupil.subject;

import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PupilSubjectDTO {
    private String name;
    private List<PupilGradeDTO> grades;
    private PupilSubjectProfileDTO pupilSubjectProfileDTO;
}
