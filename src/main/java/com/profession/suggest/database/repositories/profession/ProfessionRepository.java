package com.profession.suggest.database.repositories.profession;

import com.profession.suggest.database.entities.professions.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    Profession findByName(String name);
}
