package com.profession.suggest.database.repositories.dataanalys.psychtests;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import com.profession.suggest.database.entities.pupil.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

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
}
