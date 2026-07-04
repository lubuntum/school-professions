package com.profession.suggest.database.repositories.dataanalys.comparison;

import com.profession.suggest.database.entities.dataanalys.comparison.ComparisonSample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComparisonSampleRepository extends JpaRepository<ComparisonSample, Long> {
    List<ComparisonSample> findByCollectionId(Long collectionId);
    void deleteByCollectionId(Long collectionId);
}
