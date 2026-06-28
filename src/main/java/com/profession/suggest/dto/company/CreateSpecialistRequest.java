package com.profession.suggest.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecialistRequest {
    // Account fields
    private String email;
    private String password;
    private AllowedRole role;
    //HR fields (as Specialist)
    private String name;
    private String surname;
    private String patronymic;
    // Company fields
    private String companyName;
    private String companyInn;
    private String companyOgrn;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;
}
