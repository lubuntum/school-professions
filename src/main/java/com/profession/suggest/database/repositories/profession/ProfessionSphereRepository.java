package com.profession.suggest.database.repositories.profession;

import com.profession.suggest.database.entities.professions.ProfessionSphere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionSphereRepository extends JpaRepository<ProfessionSphere, Long> {
    ProfessionSphere findByName(String name);
}
