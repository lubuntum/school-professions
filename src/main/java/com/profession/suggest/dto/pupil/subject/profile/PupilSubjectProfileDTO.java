package com.profession.suggest.dto.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.subject.profile.InterestLevel;
import com.profession.suggest.database.entities.pupil.subject.profile.ParticipationLevel;
import com.profession.suggest.database.entities.pupil.subject.profile.ProbabilityLevel;
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
