package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import com.profession.suggest.database.entities.pupil.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsychTestRepository extends JpaRepository<PsychTest, Long> {
    List<PsychTest> findByPupil(Pupil pupil);
}
