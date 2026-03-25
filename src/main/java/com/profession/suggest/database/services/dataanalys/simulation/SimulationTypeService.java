package com.profession.suggest.database.services.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.SimulationType;
import com.profession.suggest.database.repositories.dataanalys.simulation.SimulationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimulationTypeService {
    private final SimulationTypeRepository repository;

    public SimulationType getByName(String name) {
        return repository.findByName(name);
    }
}
