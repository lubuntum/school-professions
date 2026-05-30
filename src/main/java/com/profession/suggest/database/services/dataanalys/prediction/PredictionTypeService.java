package com.profession.suggest.database.services.dataanalys.prediction;

import com.profession.suggest.database.entities.dataanalys.prediction.PredictionType;
import com.profession.suggest.database.entities.dataanalys.prediction.PredictionTypeEnum;
import com.profession.suggest.database.repositories.dataanalys.prediction.PredictionTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PredictionTypeService {
    private final PredictionTypeRepository repository;

    public List<PredictionType> getAll() {
        return repository.findAll();
    }
    public PredictionType getByName(PredictionTypeEnum name) {
        return repository.findByName(name);
    }

}
