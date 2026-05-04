package com.profession.suggest.database.services.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.Scenario;
import com.profession.suggest.database.repositories.dataanalys.simulation.ScenarioRepository;
import com.profession.suggest.dto.dataanalys.simulation.ScenarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository repository;

    public Scenario getScenarioByName(String name) {
        return repository.findByName(name);
    }
    public List<ScenarioDTO> getAllScenarios() {
        return repository.findAll().stream()
                .map(scenario -> new ScenarioDTO(scenario.getId(), scenario.getName()))
                .collect(Collectors.toList());
    }
}
