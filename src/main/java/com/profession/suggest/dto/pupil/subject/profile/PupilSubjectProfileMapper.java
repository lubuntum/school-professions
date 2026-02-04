package com.profession.suggest.dto.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.subject.profile.PupilSubjectProfile;
import org.springframework.stereotype.Component;

@Component
public class PupilSubjectProfileMapper {
    public PupilSubjectProfile fromDTO (PupilSubjectProfileDTO dto) {
        PupilSubjectProfile profile = new PupilSubjectProfile();
        profile.setInterestLevel(dto.getInterestLevel());
        profile.setContest(dto.getContestParticipationLevel());
        profile.setPracticalProject(dto.getProjectParticipationLevel());
        profile.setProbabilityLevel(dto.getSelectionProbabilityLevel());
        return profile;
    }
    public PupilSubjectProfileDTO toDTO (PupilSubjectProfile profile) {
        PupilSubjectProfileDTO dto = new PupilSubjectProfileDTO();
        dto.setInterestLevel(profile.getInterestLevel());
        dto.setContestParticipationLevel(profile.getContest());
        dto.setProjectParticipationLevel(profile.getPracticalProject());
        dto.setSelectionProbabilityLevel(profile.getProbabilityLevel());
        return dto;
    }
}
