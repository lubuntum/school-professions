package com.profession.suggest.dto.company;

import com.profession.suggest.database.entities.users.specialist.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyDTO toDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setInn(company.getInn());
        dto.setEmail(company.getEmail());
        dto.setOgrn(company.getOgrn());
        dto.setAddress(company.getAddress());
        dto.setPhone(company.getPhone());
        return dto;
    }
}
