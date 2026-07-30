package com.profession.suggest.dto.company;

import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.gender.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String fullName;
    private String email;
    private List<RoleEnum> roles;  // "HR" or "SPECIALIST"
    //additional fields for HR data
    private String contactEmail;
    private String contactPhone;
    private String experience;
    private String jobSatisfaction;
    private String profession;
    private GenderEnum gender;
}
