package com.profession.suggest.dto.specialist;

import com.profession.suggest.database.entities.specialist.Specialist;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;

public class SpecialistRegisterRequest {
    AccountRegisterRequestDTO account;
    SpecialistDTO specialist;
}
