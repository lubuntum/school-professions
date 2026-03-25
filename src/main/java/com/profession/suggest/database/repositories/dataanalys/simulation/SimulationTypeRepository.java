package com.profession.suggest.database.repositories.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.SimulationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationTypeRepository extends JpaRepository<SimulationType, Long> {
    SimulationType findByName(String name);

}
