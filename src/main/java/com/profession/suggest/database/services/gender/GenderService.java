package com.profession.suggest.database.services.gender;

import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.gender.GenderEnum;
import com.profession.suggest.database.repositories.gender.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenderService {
    private final GenderRepository repository;

    public Gender findGenderByName(GenderEnum name) {
        return repository.findByName(name);
    }
}
