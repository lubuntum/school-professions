package com.profession.suggest.database.repositories.gender;

import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.gender.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findByName(GenderEnum name);
}
