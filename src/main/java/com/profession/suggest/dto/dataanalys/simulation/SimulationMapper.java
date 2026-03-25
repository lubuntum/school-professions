package com.profession.suggest.dto.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.Simulation;
import com.profession.suggest.database.entities.dataanalys.simulation.SimulationType;
import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.entities.pupil.Pupil;
import org.springframework.stereotype.Component;

@Component
public class SimulationMapper {
    public Simulation fromDTO(SimulationDTO dto,
                              SimulationType simulationType,
                              Profession profession,
                              Pupil pupil,
                              String filepath) {
        Simulation simulation = new Simulation();
        simulation.setStartSimulation(dto.getStartSimulation());
        simulation.setEndSimulation(dto.getEndSimulation());
        simulation.setSimulationType(simulationType);
        simulation.setProfession(profession);
        simulation.setPupil(pupil);
        simulation.setFilePath(filepath);
        return simulation;
    }
    public SimulationDTO toDTO(Simulation simulation, String email) {
        SimulationDTO dto = new SimulationDTO();
        dto.setEmail(email);
        dto.setStartSimulation(simulation.getStartSimulation());
        dto.setEndSimulation(simulation.getEndSimulation());
        dto.setProfession(simulation.getProfession().getName());
        dto.setSimulationType(simulation.getSimulationType().getName());
        dto.setFilePath(simulation.getFilePath());
        dto.setCreatedAt(simulation.getCreatedAt());
        return dto;
    }
    public SimulationResponseDTO toResponseDTO(Simulation simulation, String email) {
        SimulationDTO dto = toDTO(simulation, email);
        SimulationResponseDTO simulationResponseDTO = new SimulationResponseDTO();

        simulationResponseDTO.setSimulation(dto);
        simulationResponseDTO.setId(simulation.getId());
        return simulationResponseDTO;
    }
}
