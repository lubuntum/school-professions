package com.profession.suggest.dto.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParamName;
import com.profession.suggest.database.services.dataanalys.psychtests.PsychParamNameService;
import org.springframework.stereotype.Component;

@Component
public class PsychParamMapper {
    private final PsychParamNameService psychParamNameService;

    public PsychParamMapper(PsychParamNameService psychParamNameService) {
        this.psychParamNameService = psychParamNameService;
    }

    public PsychParam fromDTO (PsychParamDTO dto) {
        PsychParam psychParam = new PsychParam();
        psychParam.setParam(dto.getParam());
        psychParam.setPsychParamName(psychParamNameService.getPsychParamNameByName(dto.getName()));
        return psychParam;
    }
}
