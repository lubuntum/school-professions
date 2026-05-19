package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import com.profession.suggest.database.entities.users.pupil.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PsychTestRepository extends JpaRepository<PsychTest, Long> {
    List<PsychTest> findByPupil(Pupil pupil);
    List<PsychTest> findByPupilAndPsychTestType(Pupil pupil, PsychTestType testType);

    @Query("select distinct pt from PsychTest pt " +
            "left join pt.pupil p " +
            "left join pt.specialist s " +
            "where p.account.id = :accountId or s.account.id = :accountId")
    List<PsychTest> findByAccountId(@Param("accountId") Long accountId);
    @Query("select distinct pt from PsychTest pt " +
            "left join pt.pupil p " +
            "left join pt.specialist s " +
            "where (p.account.id = :accountId or s.account.id = :accountId) and " +
            "pt.psychTestType.name = :testTypeName")
    List<PsychTest> findByAccountIdAndTestType(@Param("accountId") Long accountId, @Param("testTypeName") String testTypeName);
    @Query("select distinct pt from PsychTest pt " +
            "inner join pt.pupil p " +
            "where pt.createdAt >= :startDate and pt.createdAt <= :endDate")
    List<PsychTest> findByPupilAndDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    @Query("select distinct pt from PsychTest pt " +
            "inner join pt.specialist s " +
            "where pt.createdAt >= :startDate and pt.createdAt <= :endDate")
    List<PsychTest> findBySpecialistAndDateRange(@Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);


    @Query("select distinct pt from PsychTest pt " +
            "where pt.createdAt >= :startDate and pt.createdAt <= :endDate")
    List<PsychTest> findByDateRange(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);
}
