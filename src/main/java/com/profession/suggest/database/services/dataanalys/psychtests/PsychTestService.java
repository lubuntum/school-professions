package com.profession.suggest.database.services.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychParamRepository;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychTestRepository;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.psychtests.PsychParamDTO;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestPupilRequestDTO;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestPupilRequestMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PsychTestService {
    private final PsychTestRepository repository;
    private final PsychParamService psychParamService;
    private final PupilService pupilService;
    private final PsychTestTypeService psychTestTypeService;
    private final PsychTestPupilRequestMapper mapper;

    @Transactional
    public void createPsychTestForPupil(PsychTestPupilRequestDTO psychTestPupilRequestDTO) {
        if (psychTestPupilRequestDTO.getPupilId() == null)
            throw new IllegalArgumentException("Pupil id is null");
        if (psychTestPupilRequestDTO.getPsychParams() == null)
            throw new IllegalArgumentException("psychParams list is null or empty");
        if (psychTestPupilRequestDTO.getTestTypeName() == null || psychTestPupilRequestDTO.getTestTypeName().isBlank())
            throw new IllegalArgumentException("TestTypeName is null");
        Pupil pupil = pupilService.getPupilById(psychTestPupilRequestDTO.getPupilId());
        PsychTestType psychTestType = psychTestTypeService.getPsychTestTypeByName(psychTestPupilRequestDTO.getTestTypeName());

        PsychTest psychTest = mapper.fromDTO(psychTestPupilRequestDTO);
        Set<PsychParam> params = new HashSet<>();
        for (PsychParam param : psychTest.getPsychParams()) {
            PsychParam savedParam = psychParamService.create(param);
            params.add(savedParam);
        }
        psychTest.setPsychParams(params);
        psychTest.setPsychTestType(psychTestType);
        psychTest.setPupil(pupil);

        repository.save(psychTest);
    }
}
