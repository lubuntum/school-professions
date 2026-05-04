package com.profession.suggest.database.services.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.SimulationDataSource;
import com.profession.suggest.database.repositories.dataanalys.simulation.SimulationDataSourceRepository;
import com.profession.suggest.dto.dataanalys.simulation.SimulationDataSourceDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimulationDataSourceService {
    private final SimulationDataSourceRepository repository;

    public List<SimulationDataSourceDTO> getSimulationDataSources() {
        return repository.findAll()
                .stream()
                .map(dS -> new SimulationDataSourceDTO(dS.getId(), dS.getName()))
                .collect(Collectors.toList());
    }
    public SimulationDataSource getByName(String name) {
        return repository.findByName(name);
    }
}
