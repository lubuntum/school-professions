package com.profession.suggest.dto.dataanalys.psychtests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychTestDTO {
    private Double completionTimeSeconds;
    private List<PsychParamDTO> psychParams;
    private String testTypeName;
    private LocalDateTime createdAt;
}
