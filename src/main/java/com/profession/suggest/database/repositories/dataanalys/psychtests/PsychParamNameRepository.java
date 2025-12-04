package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParamName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychParamNameRepository extends JpaRepository<PsychParamName, Long> {
    PsychParamName findByName(String name);
}
