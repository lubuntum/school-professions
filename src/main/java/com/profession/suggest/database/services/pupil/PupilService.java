package com.profession.suggest.database.services.pupil;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.pupil.PupilRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.auth.role.RoleService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.auth.AccountMapper;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.dto.pupil.PupilMapper;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilService {
    private final PupilRepository repository;
    private final AccountService accountService;
    private final RoleService roleService;
    private final PupilMapper pupilMapper;
    private final AccountMapper accountMapper;

    public Pupil getPupilById(Long id) {
        return repository.getReferenceById(id);
    }
    public Pupil create(Pupil pupil) {
        return repository.save(pupil);
    }
    @Transactional
    public Pupil createWithAccount(AccountApiRegisterDTO accountApiRegisterDTO) {
        Pupil pupil = pupilMapper.fromDTO(accountApiRegisterDTO.getPupilDTO());
        Account account = accountMapper.fromDTO(accountApiRegisterDTO.getAccountRegisterRequestDTO());
        Role role = roleService.findByName(RoleEnum.PUPIL);
        pupil.setAccount(account);
        account.setPupil(pupil);

        if (account.getRoles() == null) account.setRoles(new HashSet<>());
        account.getRoles().add(role);

        return repository.save(pupil);
    }
    public Page<PupilResponseDTO> getPupilsData(Pageable pageable) {
        return repository.findPupilsData(pageable);
    }
}
