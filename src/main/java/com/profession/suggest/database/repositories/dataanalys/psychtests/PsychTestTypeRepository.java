package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychTestTypeRepository extends JpaRepository<PsychTestType, Long> {
    PsychTestType findByName(String name);
}
