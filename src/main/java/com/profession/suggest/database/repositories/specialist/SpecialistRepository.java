package com.profession.suggest.database.repositories.specialist;

import com.profession.suggest.database.entities.specialist.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}
