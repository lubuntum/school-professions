package com.profession.suggest.database.services.dataanalys.vrtests;

import com.profession.suggest.database.entities.dataanalys.vrtests.VRTestType;
import com.profession.suggest.database.repositories.dataanalys.vrtests.VRTestTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VRTestTypeService {
    private final VRTestTypeRepository repository;

    public VRTestType getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(()-> new IllegalArgumentException("No such type"));
    }
}
