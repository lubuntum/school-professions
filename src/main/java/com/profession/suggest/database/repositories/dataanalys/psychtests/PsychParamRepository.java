package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychParamRepository extends JpaRepository<PsychParam, Long> {
}
