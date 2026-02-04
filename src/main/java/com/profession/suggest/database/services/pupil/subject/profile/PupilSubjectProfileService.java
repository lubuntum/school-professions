package com.profession.suggest.database.services.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.entities.pupil.subject.profile.PupilSubjectProfile;
import com.profession.suggest.database.repositories.pupil.subject.profile.PupilSubjectProfileRepository;
import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileDTO;
import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileMapper;
import org.springframework.stereotype.Service;

@Service
public class PupilSubjectProfileService {
    private final PupilSubjectProfileRepository repository;
    private final PupilSubjectProfileMapper mapper;

    public PupilSubjectProfileService(PupilSubjectProfileRepository repository, PupilSubjectProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public PupilSubjectProfile createPupilSubjectProfile(Pupil pupil, Subject subject, PupilSubjectProfileDTO profileDTO) {
        PupilSubjectProfile pupilSubjectProfile = repository
                .findByPupilAndSubject(pupil, subject)
                .orElseGet(() -> {
                    PupilSubjectProfile profile = new PupilSubjectProfile();
                    profile.setPupil(pupil);
                    profile.setSubject(subject);
                    return profile;
                });
        if (profileDTO.getInterestLevel() != null) pupilSubjectProfile.setInterestLevel(profileDTO.getInterestLevel());
        if (profileDTO.getSelectionProbabilityLevel() != null) pupilSubjectProfile.setProbabilityLevel(profileDTO.getSelectionProbabilityLevel());
        if (profileDTO.getContestParticipationLevel() != null) pupilSubjectProfile.setContest(profileDTO.getContestParticipationLevel());
        if (profileDTO.getProjectParticipationLevel() != null) pupilSubjectProfile.setPracticalProject(profileDTO.getProjectParticipationLevel());
        return repository.save(pupilSubjectProfile);
    }
    public PupilSubjectProfileDTO getProfileByPupil(Pupil pupil) {
        PupilSubjectProfile profile = repository.findByPupil(pupil);
        if (profile == null) return new PupilSubjectProfileDTO();

        return mapper.toDTO(repository.findByPupil(pupil));
    }
}
