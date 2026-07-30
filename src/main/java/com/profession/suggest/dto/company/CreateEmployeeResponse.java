package com.profession.suggest.dto.company;

import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeResponse {
    private Long accountId;
    private Long companyId;
    private String email;
    private String fullName;
    private String companyName;
    private List<RoleEnum> roles;

}
