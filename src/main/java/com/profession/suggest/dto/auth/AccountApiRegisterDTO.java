package com.profession.suggest.dto.auth;

import com.profession.suggest.dto.pupil.PupilDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountApiRegisterDTO {
    @Valid
    AccountRegisterRequestDTO accountRegisterRequestDTO;
    @Valid
    PupilDTO pupilDTO;
}
