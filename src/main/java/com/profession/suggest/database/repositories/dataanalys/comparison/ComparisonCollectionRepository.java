package com.profession.suggest.database.repositories.dataanalys.comparison;

import com.profession.suggest.database.entities.dataanalys.comparison.ComparisonCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ComparisonCollectionRepository extends JpaRepository<ComparisonCollection, Long> {
    Optional<ComparisonCollection> findByName(String name);

    List<ComparisonCollection> findByIsActiveTrue();

    @Query("SELECT c FROM ComparisonCollection c WHERE c.isActive = true ORDER BY c.createdAt DESC")
    Page<ComparisonCollection> findActiveCollectionsOrdered(Pageable pageable);
    @Query("SELECT c FROM ComparisonCollection c WHERE c.isActive = true ORDER BY c.createdAt DESC")
    List<ComparisonCollection> findActiveCollectionsOrdered();

    @Query("SELECT c FROM ComparisonCollection c LEFT JOIN FETCH c.samples WHERE c.id = :id")
    Optional<ComparisonCollection> findByIdWithSamples(@Param("id") Long id);
}
