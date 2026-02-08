package com.profession.suggest.database.services.pupil.subject;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.repositories.pupil.subject.SubjectRepository;
import com.profession.suggest.database.services.pupil.subject.profile.PupilSubjectProfileService;
import com.profession.suggest.dto.pupil.subject.PupilSubjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository repository;
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }
    public Subject getSubjectByName(String name) {
        return repository.findByName(name);
    }
    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public List<Subject> getSubjectsByNames(List<String> names) {
        return repository.findAllByNameIn(names);
    }
}
