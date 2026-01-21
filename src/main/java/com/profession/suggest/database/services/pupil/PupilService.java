package com.profession.suggest.database.services.pupil;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.pupil.PupilRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.auth.role.RoleService;
import com.profession.suggest.database.services.gender.GenderService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.auth.AccountMapper;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.dto.pupil.PupilMapper;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilService {
    private final PupilRepository repository;
    private final AccountService accountService;
    private final GenderService genderService;
    private final RoleService roleService;
    private final PupilMapper pupilMapper;
    private final AccountMapper accountMapper;

    public Pupil getPupilById(Long id) {
        return repository.getReferenceById(id);
    }
    public Pupil getPupilByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
    public Pupil create(Pupil pupil) {
        return repository.save(pupil);
    }
    @Transactional
    public PupilDTO createWithAccount(AccountApiRegisterDTO accountApiRegisterDTO) {
        Pupil pupil = pupilMapper.fromDTO(accountApiRegisterDTO.getPupilDTO());
        Account account = accountMapper.fromDTO(accountApiRegisterDTO.getAccountRegisterRequestDTO());
        Role role = roleService.findByName(RoleEnum.PUPIL);
        if (account.getRoles() == null) account.setRoles(new HashSet<>());
        account.getRoles().add(role);
        Account savedAccount = accountService.createAccount(account);
        Gender gender = genderService.findGenderByName(accountApiRegisterDTO.getPupilDTO().getGender());
        pupil.setGender(gender);
        pupil.setAccount(savedAccount);

        Pupil savedPupil = repository.save(pupil);
        return pupilMapper.toDTO(savedPupil);
    }

    public void createAllWithAccount(List<AccountApiRegisterDTO> accounts) {
        try {
            accounts.forEach(this::createWithAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public PupilDTO updatePupilData(PupilDTO pupilDTO, Long accountId) throws AccountNotFoundException {
        Pupil pupil = getPupilByAccountId(accountId);
        Account account = accountService.getAccountById(accountId);
        Gender gender = genderService.findGenderByName(pupilDTO.getGender());
        if (pupil == null) {
            pupil = new Pupil();
            pupil.setAccount(account);
        }
        pupil = pupilMapper.updateFromDTO(pupil, pupilDTO);
        pupil.setGender(gender);
        return pupilMapper.toDTO(repository.save(pupil));
    }
    public Page<PupilResponseDTO> getPupilsData(Pageable pageable) {
        return repository.findPupilsData(pageable);
    }
    public PupilResponseDTO getPupilData(Long id) {
        return repository.findPupilData(id);
    }
}
