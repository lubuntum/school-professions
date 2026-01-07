package com.profession.suggest.dto.dataanalys.psychtests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychTestPupilRequestDTO {
    private Double completionTimeSeconds;
    private List<PsychParamDTO> psychParams;
    private String testTypeName;
}
