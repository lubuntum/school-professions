package com.profession.suggest.database.services.specialist;

import com.profession.suggest.database.repositories.specialist.SpecialistRepository;

public class SpecialistService {
    private final SpecialistRepository repository;

    public SpecialistService(SpecialistRepository repository) {
        this.repository = repository;
    }
}
