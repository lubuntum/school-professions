package com.profession.suggest.database.services.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychTestRepository;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestDTO;
import com.profession.suggest.dto.dataanalys.psychtests.PsychTestMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PsychTestService {
    private final PsychTestRepository repository;
    private final PsychParamService psychParamService;
    private final PupilService pupilService;
    private final PsychTestTypeService psychTestTypeService;
    private final PsychTestMapper mapper;

    @Transactional
    public PsychTestDTO createPsychTestForPupil(PsychTestDTO psychTestDTO, Pupil pupil) {
        if (pupil == null)
            throw new IllegalArgumentException("Pupil is not found");
        if (psychTestDTO.getPsychParams() == null)
            throw new IllegalArgumentException("psychParams list is null or empty");
        if (psychTestDTO.getTestTypeName() == null || psychTestDTO.getTestTypeName().isBlank())
            throw new IllegalArgumentException("TestTypeName is null");
        PsychTestType psychTestType = psychTestTypeService.getPsychTestTypeByName(psychTestDTO.getTestTypeName());

        PsychTest psychTest = mapper.fromDTO(psychTestDTO);
        Set<PsychParam> params = new HashSet<>();
        for (PsychParam param : psychTest.getPsychParams()) {
            PsychParam savedParam = psychParamService.create(param);
            params.add(savedParam);
        }
        psychTest.setPsychParams(params);
        psychTest.setPsychTestType(psychTestType);
        psychTest.setPupil(pupil);

        return mapper.toDTO(repository.save(psychTest));
    }
    public List<PsychTestDTO> getPupilTests(Pupil pupil) {
        List<PsychTest> psychTests = repository.findByPupil(pupil);
        return psychTests.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
    public List<PsychTestDTO> getPupilTestByType(Pupil pupil, String testTypeName) {
        PsychTestType testType = psychTestTypeService.getPsychTestTypeByName(testTypeName);
        List<PsychTest> psychTests = repository.findByPupilAndPsychTestType(pupil, testType);
        return psychTests.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
