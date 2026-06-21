package com.profession.suggest.database.repositories.dataanalys.vrtests;

import com.profession.suggest.database.entities.dataanalys.vrtests.VRTestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VRTestTypeRepository extends JpaRepository<VRTestType, Long> {
    Optional<VRTestType> findByName(String name);

    boolean existsByName(String name);
}
