package com.profession.suggest.database.repositories.dataanalys.prediction;

import com.profession.suggest.database.entities.dataanalys.prediction.PredictionType;
import com.profession.suggest.database.entities.dataanalys.prediction.PredictionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionTypeRepository extends JpaRepository<PredictionType, Long> {
    PredictionType findByName(PredictionTypeEnum name);
}
