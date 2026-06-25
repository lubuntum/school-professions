package com.profession.suggest.database.repositories.specialist;


import com.profession.suggest.database.entities.users.specialist.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(@Param("name") String name);
}
