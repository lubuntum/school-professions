package com.profession.suggest.database.repositories.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SimulationRepository extends JpaRepository<Simulation, Long>,
        JpaSpecificationExecutor<Simulation> {
}
