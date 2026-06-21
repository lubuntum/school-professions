package com.profession.suggest.database.services.profession;

import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.repositories.profession.ProfessionRepository;
import com.profession.suggest.dto.specialist.ProfessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessionService {
    private final ProfessionRepository repository;

    public Profession getProfessionByName(String name) {
        return repository.findByName(name);
    }
    public List<ProfessionDTO> getProfessions() {
        return repository.findAll().stream()
                .map(profession -> new ProfessionDTO(profession.getId(), profession.getName()))
                .collect(Collectors.toList());
    }
    public ProfessionDTO createProfession(ProfessionDTO professionDTO) {
        if (professionDTO.getName() == null || professionDTO.getName().isEmpty())
            throw new IllegalArgumentException("Profession name cant be empty");
        Profession profession = new Profession();
        profession.setName(professionDTO.getName());
        Profession result = repository.save(profession);
        return new ProfessionDTO(result.getId(), result.getName());
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public Profession getProfessionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cant find profession by id " + id));
    }
}
