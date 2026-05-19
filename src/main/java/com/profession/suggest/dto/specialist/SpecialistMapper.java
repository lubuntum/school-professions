package com.profession.suggest.dto.specialist;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.users.specialist.Specialist;
import org.springframework.stereotype.Component;

@Component
public class SpecialistMapper {
    public SpecialistDTO toDTO(Specialist specialist, Account account) {
        SpecialistDTO dto = new SpecialistDTO();
        dto.setId(specialist.getId());
        dto.setEmail(account.getEmail());
        dto.setName(specialist.getName());
        dto.setSurname(specialist.getSurname());
        dto.setPatronymic(specialist.getPatronymic());
        dto.setContactEmail(specialist.getContactEmail());
        dto.setContactPhone(specialist.getContactPhone());
        if (specialist.getProfession() != null)
            dto.setProfession(specialist.getProfession().getName());
        if (specialist.getGender() != null)
            dto.setGender(specialist.getGender().getName());
        dto.setJobSatisfaction(specialist.getJobSatisfaction());
        dto.setExperience(specialist.getExperience());
        return dto;
    }
    public Specialist fromDTO(SpecialistDTO dto) {
        Specialist specialist = new Specialist();
        specialist.setName(dto.getName());
        specialist.setSurname(dto.getSurname());
        specialist.setPatronymic(dto.getPatronymic());
        specialist.setContactEmail(dto.getContactEmail());
        specialist.setContactPhone(dto.getContactPhone());
        specialist.setExperience(dto.getExperience());
        specialist.setJobSatisfaction(dto.getJobSatisfaction());
        return specialist;
    }
}
