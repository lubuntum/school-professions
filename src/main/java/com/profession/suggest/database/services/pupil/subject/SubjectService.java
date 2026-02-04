package com.profession.suggest.database.services.pupil.subject;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.repositories.pupil.subject.SubjectRepository;
import com.profession.suggest.database.services.pupil.subject.profile.PupilSubjectProfileService;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository repository;
    private final PupilSubjectProfileService pupilSubjectProfileService;
    public SubjectService(SubjectRepository repository, PupilSubjectProfileService pupilSubjectProfileService) {
        this.repository = repository;
        this.pupilSubjectProfileService = pupilSubjectProfileService;
    }
    public Subject getSubjectByName(String name) {
        return repository.findByName(name);
    }
    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }
    @Transactional
    public void addSubjectProfilesForPupil(List<PupilSubjectDTO> pupilSubjectDTOS, Pupil pupil) {
        pupilSubjectDTOS.forEach((pupilSubjectDTO -> {
            Subject subject = repository.findByName(pupilSubjectDTO.getName());
            pupilSubjectProfileService.createPupilSubjectProfile(pupil, subject, pupilSubjectDTO.getPupilSubjectProfileDTO());
        }));
    }
}
