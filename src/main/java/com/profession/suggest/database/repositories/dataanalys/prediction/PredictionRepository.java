package com.profession.suggest.database.repositories.dataanalys.prediction;

import com.profession.suggest.database.entities.dataanalys.prediction.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
}
