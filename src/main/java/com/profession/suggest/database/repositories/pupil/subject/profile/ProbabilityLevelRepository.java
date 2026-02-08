package com.profession.suggest.database.repositories.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.subject.profile.ProbabilityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbabilityLevelRepository extends JpaRepository<ProbabilityLevel, Long> {
    ProbabilityLevel findByLevel(String level);
}
