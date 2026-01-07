package com.profession.suggest.dto.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PsychTestPupilRequestMapper {
    private final PsychParamMapper psychParamMapper;

    public PsychTestPupilRequestMapper(PsychParamMapper psychParamMapper) {
        this.psychParamMapper = psychParamMapper;
    }

    public PsychTest fromDTO(PsychTestPupilRequestDTO dto) {
        PsychTest psychTest = new PsychTest();
        psychTest.setCompletionTimeSeconds(dto.getCompletionTimeSeconds());
        psychTest.setPsychParams(
                dto.getPsychParams().stream()
                        .map(psychParamMapper::fromDTO)
                        .collect(Collectors.toSet()));
        return psychTest;
    }
    public PsychTestPupilRequestDTO toDTO(PsychTest psychTest){
        PsychTestPupilRequestDTO dto = new PsychTestPupilRequestDTO();
        dto.setCompletionTimeSeconds(psychTest.getCompletionTimeSeconds());
        dto.setTestTypeName(psychTest.getPsychTestType().getName());
        dto.setPsychParams(psychTest.getPsychParams().stream().map(psychParamMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

}
