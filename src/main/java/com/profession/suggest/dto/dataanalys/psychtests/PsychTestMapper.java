package com.profession.suggest.dto.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PsychTestMapper {
    private final PsychParamMapper psychParamMapper;

    public PsychTestMapper(PsychParamMapper psychParamMapper) {
        this.psychParamMapper = psychParamMapper;
    }

    public PsychTest fromDTO(PsychTestDTO dto) {
        PsychTest psychTest = new PsychTest();
        psychTest.setCompletionTimeSeconds(dto.getCompletionTimeSeconds());
        psychTest.setPsychParams(
                dto.getPsychParams().stream()
                        .map(psychParamMapper::fromDTO)
                        .collect(Collectors.toSet()));
        return psychTest;
    }
    public PsychTestDTO toDTO(PsychTest psychTest){
        PsychTestDTO dto = new PsychTestDTO();
        dto.setCompletionTimeSeconds(psychTest.getCompletionTimeSeconds());
        dto.setTestTypeName(psychTest.getPsychTestType().getName());
        dto.setPsychParams(psychTest.getPsychParams().stream().map(psychParamMapper::toDTO).collect(Collectors.toList()));
        dto.setCreatedAt(psychTest.getCreatedAt());
        return dto;
    }

}
