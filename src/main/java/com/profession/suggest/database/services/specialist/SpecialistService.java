package com.profession.suggest.database.services.specialist;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.entities.users.specialist.Specialist;
import com.profession.suggest.database.repositories.specialist.SpecialistRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.gender.GenderService;
import com.profession.suggest.database.services.profession.ProfessionService;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import com.profession.suggest.dto.specialist.SpecialistMapper;
import com.profession.suggest.dto.specialist.SpecialistRegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {
    private final SpecialistRepository repository;
    private final ProfessionService professionService;
    private final SpecialistMapper mapper;
    private final GenderService genderService;
    private final AccountService accountService;
    public SpecialistService(SpecialistRepository repository, ProfessionService professionService, SpecialistMapper mapper, GenderService genderService, AccountService accountService) {
        this.repository = repository;
        this.professionService = professionService;
        this.mapper = mapper;
        this.genderService = genderService;
        this.accountService = accountService;
    }
    public Page<SpecialistDTO> getSpecialistsPage(Pageable pageable) {
        return repository.findSpecialists(pageable);
    }
    //TODO check if profession is null
    public SpecialistDTO create(SpecialistDTO dto, Account account) {
        Specialist specialist = new Specialist();
        if (dto.getProfession() != null && !dto.getProfession().isEmpty()) {
            Profession profession = professionService.getProfessionByName(dto.getProfession());
            specialist.setProfession(profession);
        }
        if (dto.getGender() != null && !dto.getGender().toString().isEmpty()){
            Gender gender = genderService.findGenderByName(dto.getGender());
            specialist.setGender(gender);
        }

        specialist.setName(dto.getName());
        specialist.setSurname(dto.getSurname());
        specialist.setPatronymic(dto.getPatronymic());
        specialist.setExperience(dto.getExperience());
        specialist.setJobSatisfaction(dto.getJobSatisfaction());
        specialist.setContactPhone(dto.getContactPhone());
        specialist.setContactEmail(dto.getContactEmail());


        specialist.setAccount(account);
        return mapper.toDTO(repository.save(specialist), account);
    }
    public void createAllWithAccounts(List<SpecialistRegisterRequest> specialistsRequests) {
        for (SpecialistRegisterRequest specialistRequest: specialistsRequests) {
            try {
                Account account = accountService.registration(specialistRequest.getAccount(), RoleEnum.SPECIALIST);
                create(specialistRequest.getSpecialist(), account);
            } catch (Exception e) {
                System.err.println("Error processing account ");
            }
        }
    }
    public SpecialistDTO getSpecialistByAccount(Account account) {
        if (account.getSpecialist() == null)
            throw new NullPointerException("Account doesn't have specialist data");
        return mapper.toDTO(account.getSpecialist(), account);
    }
    public SpecialistDTO update(SpecialistDTO specialistDTO) {
        Specialist specialist = repository.findById(specialistDTO.getId())
                .orElseThrow(() -> new NullPointerException("Specialist not found"));
        Account account = specialist.getAccount();
        Gender gender = genderService.findGenderByName(specialistDTO.getGender());
        Profession profession = professionService.getProfessionByName(specialistDTO.getProfession());

        specialist.setName(specialistDTO.getName());
        specialist.setSurname(specialistDTO.getSurname());
        specialist.setPatronymic(specialistDTO.getPatronymic());
        specialist.setExperience(specialistDTO.getExperience());
        specialist.setJobSatisfaction(specialistDTO.getJobSatisfaction());
        specialist.setContactEmail(specialistDTO.getContactEmail());
        specialist.setContactPhone(specialistDTO.getContactPhone());
        if (gender != null)
            specialist.setGender(gender);
        if (profession != null)
            specialist.setProfession(profession);
        return mapper.toDTO(repository.save(specialist), account);
    }
}
