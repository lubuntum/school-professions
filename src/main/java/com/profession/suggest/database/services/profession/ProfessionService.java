package com.profession.suggest.database.services.profession;

import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.repositories.profession.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessionService {
    private final ProfessionRepository repository;

    public Profession getProfessionByName(String name) {
        return repository.findByName(name);
    }
}
