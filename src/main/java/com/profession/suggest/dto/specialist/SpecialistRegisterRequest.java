package com.profession.suggest.dto.specialist;

import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialistRegisterRequest {
    AccountRegisterRequestDTO account;
    SpecialistDTO specialist;
}
