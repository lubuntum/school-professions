package com.profession.suggest.database.repositories.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.SimulationDataSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationDataSourceRepository extends JpaRepository<SimulationDataSource, Long> {
    SimulationDataSource findByName(String name);
}
