package com.profession.suggest.database.services.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychParamRepository;
import org.springframework.stereotype.Service;

@Service
public class PsychParamService {
    private final PsychParamRepository repository;

    public PsychParamService(PsychParamRepository repository) {
        this.repository = repository;
    }

    public PsychParam create(PsychParam psychParam) {
        return repository.save(psychParam);
    }
}
