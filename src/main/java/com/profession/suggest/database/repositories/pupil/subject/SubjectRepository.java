package com.profession.suggest.database.repositories.pupil.subject;

import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.services.pupil.subject.SubjectService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
    List<Subject> findAllByNameIn(List<String> names);
}
