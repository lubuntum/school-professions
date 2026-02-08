package com.profession.suggest.database.services.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.entities.pupil.subject.profile.PupilSubjectProfile;
import com.profession.suggest.database.repositories.pupil.subject.profile.InterestLevelRepository;
import com.profession.suggest.database.repositories.pupil.subject.profile.ParticipationLevelRepository;
import com.profession.suggest.database.repositories.pupil.subject.profile.ProbabilityLevelRepository;
import com.profession.suggest.database.repositories.pupil.subject.profile.PupilSubjectProfileRepository;
import com.profession.suggest.database.services.pupil.subject.SubjectService;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileDTO;
import com.profession.suggest.dto.pupil.subject.profile.PupilSubjectProfileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PupilSubjectProfileService {
    private final PupilSubjectProfileRepository repository;
    private final PupilSubjectProfileMapper mapper;
    private final InterestLevelRepository interestLevelRepository;
    private final ProbabilityLevelRepository probabilityLevelRepository;
    private final ParticipationLevelRepository participationLevelRepository;
    private final SubjectService subjectService;

    public PupilSubjectProfileService(PupilSubjectProfileRepository repository, PupilSubjectProfileMapper mapper, InterestLevelRepository interestLevelRepository, ProbabilityLevelRepository probabilityLevelRepository, ParticipationLevelRepository participationLevelRepository, SubjectService subjectService) {
        this.repository = repository;
        this.mapper = mapper;
        this.interestLevelRepository = interestLevelRepository;
        this.probabilityLevelRepository = probabilityLevelRepository;
        this.participationLevelRepository = participationLevelRepository;
        this.subjectService = subjectService;
    }
    @Transactional
    public void addSubjectProfilesForPupil(List<PupilSubjectDTO> pupilSubjectDTOS, Pupil pupil) {
        pupilSubjectDTOS.forEach((pupilSubjectDTO -> {
            Subject subject = subjectService.getSubjectByName(pupilSubjectDTO.getName());
            createPupilSubjectProfile(pupil, subject, pupilSubjectDTO.getPupilSubjectProfileDTO());
        }));
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

        if (profileDTO.getInterestLevel() != null)
            pupilSubjectProfile.setInterestLevel(interestLevelRepository.findByLevel(profileDTO.getInterestLevel()));
        if (profileDTO.getSelectionProbabilityLevel() != null)
            pupilSubjectProfile.setProbabilityLevel(probabilityLevelRepository.findByLevel(profileDTO.getSelectionProbabilityLevel()));
        if (profileDTO.getContestParticipationLevel() != null)
            pupilSubjectProfile.setContest(participationLevelRepository.findByLevel(profileDTO.getContestParticipationLevel()));
        if (profileDTO.getProjectParticipationLevel() != null)
            pupilSubjectProfile.setPracticalProject(participationLevelRepository.findByLevel(profileDTO.getProjectParticipationLevel()));
        return repository.save(pupilSubjectProfile);
    }
    public PupilSubjectProfileDTO getProfileBySubjectAndPupil(Pupil pupil, Subject subject) {
        PupilSubjectProfile profile = repository.findByPupilAndSubject(pupil, subject).orElse(null);
        if (profile == null) return null;
        return mapper.toDTO(profile);
    }
}
