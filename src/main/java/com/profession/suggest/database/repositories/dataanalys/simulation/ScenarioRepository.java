package com.profession.suggest.database.repositories.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Scenario findByName(String name);
}
