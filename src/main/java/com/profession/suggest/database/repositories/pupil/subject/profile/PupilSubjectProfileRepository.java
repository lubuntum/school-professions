package com.profession.suggest.database.repositories.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import com.profession.suggest.database.entities.pupil.subject.profile.PupilSubjectProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PupilSubjectProfileRepository extends JpaRepository<PupilSubjectProfile, Long> {
    Optional<PupilSubjectProfile> findByPupilAndSubject(Pupil pupil, Subject subject);
}
