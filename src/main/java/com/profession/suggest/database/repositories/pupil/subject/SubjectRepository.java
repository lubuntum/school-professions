package com.profession.suggest.database.repositories.pupil.subject;

import com.profession.suggest.database.entities.pupil.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
