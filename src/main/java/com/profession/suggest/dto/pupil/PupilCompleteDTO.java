package com.profession.suggest.dto.pupil;

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
public class PupilCompleteDTO {
    private Long accountId;
    private PupilDTO pupil;
    private Set<RoleEnum> roles;
    private List<PsychTestDTO> psychTests;

}
