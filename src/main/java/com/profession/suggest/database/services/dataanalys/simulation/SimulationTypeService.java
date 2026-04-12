package com.profession.suggest.database.services.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.SimulationType;
import com.profession.suggest.database.repositories.dataanalys.simulation.SimulationTypeRepository;
import com.profession.suggest.dto.dataanalys.simulation.SimulationTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationTypeService {
    private final SimulationTypeRepository repository;

    public SimulationType getByName(String name) {
        return repository.findByName(name);
    }
    public List<SimulationTypeDTO> getSimulationTypes() {
        return repository.findAll().stream()
                .map(type -> new SimulationTypeDTO(type.getName()))
                .collect(Collectors.toList());
    }
    public SimulationTypeDTO createSimulation(SimulationTypeDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Type cannot be empty");
        SimulationType simulationType = new SimulationType();
        simulationType.setName(dto.getName());
        return new SimulationTypeDTO(repository.save(simulationType).getName());
    }
}
