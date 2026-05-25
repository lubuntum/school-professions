package com.profession.suggest.dto.specialist;

import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistCompleteDTO {
    private Long accountId;
    private SpecialistDTO specialist;
    private Set<RoleEnum> roles;
    private List<PsychTestDTO> psychTests;

}
