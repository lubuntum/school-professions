package com.profession.suggest.database.repositories.pupil.subject;

import com.profession.suggest.database.entities.users.pupil.Pupil;
import com.profession.suggest.database.entities.users.pupil.subject.PupilGrade;
import com.profession.suggest.database.entities.users.pupil.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PupilGradeRepository extends JpaRepository<PupilGrade, Long> {
    Optional<PupilGrade> findByPupilAndSubjectAndClassNumber(Pupil pupil, Subject subject, Integer classNumber);
    List<PupilGrade> findByPupil(Pupil pupil);
}
