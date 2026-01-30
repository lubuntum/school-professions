package com.profession.suggest.database.services.pupil.subject;

import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.repositories.pupil.subject.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
