package com.profession.suggest.database.services.profession;

import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.entities.professions.ProfessionSphere;
import com.profession.suggest.database.repositories.profession.ProfessionSphereRepository;
import com.profession.suggest.dto.specialist.ProfessionDTO;
import com.profession.suggest.dto.specialist.ProfessionSphereDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessionSphereService {
    private final ProfessionSphereRepository repository;

    public ProfessionSphere getProfessionSphereByName(String name) {
        return repository.findByName(name);
    }
    public List<ProfessionSphereDTO> getProfessionsSpheres() {
        return repository.findAll().stream()
                .map(sphere -> new ProfessionSphereDTO(sphere.getId(), sphere.getName()))
                .collect(Collectors.toList());
    }
    public ProfessionSphereDTO create (ProfessionSphereDTO dto) {
        ProfessionSphere sphere = new ProfessionSphere();
        sphere.setName(dto.getName());
        return new ProfessionSphereDTO(repository.save(sphere).getId(), dto.getName());
    }
    public void deleteById (Long id) {
        repository.deleteById(id);
    }
}
