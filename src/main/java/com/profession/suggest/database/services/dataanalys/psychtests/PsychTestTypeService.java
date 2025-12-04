package com.profession.suggest.database.services.dataanalys.psychtests;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTestType;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychTestRepository;
import com.profession.suggest.database.repositories.dataanalys.psychtests.PsychTestTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PsychTestTypeService {
    private final PsychTestTypeRepository repository;

    public PsychTestTypeService(PsychTestTypeRepository repository) {
        this.repository = repository;
    }

    public PsychTestType getPsychTestTypeByName(String name) {
        return repository.findByName(name);
    }
}
