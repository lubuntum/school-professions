package com.profession.suggest.database.services.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParamName;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychParamNameRepository;
import org.springframework.stereotype.Service;

@Service
public class PsychParamNameService {
    private final PsychParamNameRepository repository;

    public PsychParamNameService(PsychParamNameRepository repository) {
        this.repository = repository;
    }

    public PsychParamName getPsychParamNameByName(String name) {
        return repository.findByName(name);
    }
}
