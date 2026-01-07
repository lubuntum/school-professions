package com.profession.suggest.database.repositories.auth;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);

    @Query("SELECT new com.profession.suggest.dto.pupil.PupilResponseDTO(" +
            "p.id, p.name, p.surname, p.patronymic, p.birthday, p.school, " +
            "p.healthCondition, p.nationality, p.extraActivities, p.classNumber, p.classLabel, g.name, a.email) " +
            "FROM Account a " +
            "LEFT JOIN a.pupil p " +
            "LEFT JOIN p.gender g " +
            "WHERE a.id = :id")
    PupilResponseDTO findPupilDataByAccountId(Long id);
}
