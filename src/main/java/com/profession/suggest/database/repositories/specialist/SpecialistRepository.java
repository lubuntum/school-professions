package com.profession.suggest.database.repositories.specialist;

import com.profession.suggest.database.entities.users.specialist.Specialist;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    @Query("SELECT new com.profession.suggest.dto.specialist.SpecialistDTO( " +
            "s.id, a.email, s.name, s.surname, s.patronymic, s.contactEmail, " +
            "s.contactPhone, s.experience, s.jobSatisfaction, p.name, g.name) " +
            "FROM Specialist s " +
            "LEFT JOIN Account a ON s.account.id = a.id " +
            "LEFT JOIN Gender g ON s.gender.id = g.id " +
            "LEFT JOIN s.profession p")
    Page<SpecialistDTO> findSpecialists(Pageable pageable);
}
