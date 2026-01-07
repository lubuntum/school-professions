package com.profession.suggest.database.repositories.pupil;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
    @Query("SELECT new com.profession.suggest.dto.pupil.PupilResponseDTO(" +
            "p.id, p.name, p.surname, p.patronymic, p.birthday, p.school, " +
            "p.healthCondition, p.nationality, p.extraActivities, p.classNumber, p.classLabel, g.name,  a.email) " +
            "FROM Pupil p " +
            "JOIN Account a ON p.account.id = a.id " +
            "LEFT JOIN p.gender g")
    Page<PupilResponseDTO> findPupilsData(Pageable pageable);

    @Query("SELECT new com.profession.suggest.dto.pupil.PupilResponseDTO(" +
            "p.id, p.name, p.surname, p.patronymic, p.birthday, p.school, " +
            "p.healthCondition, p.nationality, p.extraActivities, p.classNumber, p.classLabel, g.name, a.email) " +
            "FROM Pupil p " +
            "JOIN Account a ON p.account.id = a.id " +
            "LEFT JOIN p.gender g " +
            "WHERE p.id = :id")
    PupilResponseDTO findPupilData(Long id);
    Pupil findByAccountId(Long accountId);
}
