package com.profession.suggest.dto.pupil.subject.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilSubjectProfileDTO {
    String interestLevel;
    String contestParticipationLevel;
    String projectParticipationLevel;
    String selectionProbabilityLevel;
}
