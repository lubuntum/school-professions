package com.profession.suggest.dto.company;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.users.specialist.Company;
import com.profession.suggest.database.entities.users.specialist.Specialist;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CreateEmployeeMapper {
    public CreateEmployeeResponse toDTO(Account account) {
        CreateEmployeeResponse response = new CreateEmployeeResponse();
        response.setEmail(account.getEmail());
        response.setAccountId(account.getId());
        response.setRoles(account.getRoles().stream()
                .map(Role::getName).collect(Collectors.toList()));

        Specialist specialist = account.getSpecialist();
        if (specialist == null) return response;

        response.setFullName(specialist.getFullName());

        Company company = specialist.getCompany();
        if (company != null) {
            response.setCompanyId(company.getId());
            response.setCompanyName(company.getName());
        }

        return response;
    }
}
