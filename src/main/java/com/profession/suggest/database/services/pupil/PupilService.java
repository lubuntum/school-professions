package com.profession.suggest.database.services.pupil;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.gender.GenderEnum;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.pupil.PupilRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.database.services.auth.role.RoleService;
import com.profession.suggest.database.services.gender.GenderService;
import com.profession.suggest.dto.auth.AccountApiRegisterDTO;
import com.profession.suggest.dto.auth.AccountMapper;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.dto.pupil.PupilMapper;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PupilService {
    private final PupilRepository repository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final GenderService genderService;
    private final RoleService roleService;
    private final PupilMapper pupilMapper;
    private final AccountMapper accountMapper;
    @Value("${account.default.password}")
    private String defaultPassword;
    @Value("${pupil.default.name:Ivan}")
    private String defaultName;
    @Value("${pupil.default.second-name:Ivanov}")
    private String defaultSecondName;
    @Value("${pupil.default.patronymic:Ivanovich}")
    private String defaultPatronymic;
    @Value("${pupil.default.gender:MALE}")
    private GenderEnum defaultGender;
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
        account.setPassword(passwordEncoder.encode(account.getPassword()));
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
        for (AccountApiRegisterDTO account: accounts) {
            try {
                createWithAccount(account);
            } catch (Exception e) {
                System.err.println("Error processing account: " + e.getMessage());
            }
        }
    }
    //3 outcomes
    //1 Has account but not pupil
    //2 Has account and pupil
    //3 Doesnt have account and pupil
    @Transactional
    public Pupil createWithDefaults(String email) throws AccountNotFoundException {

        Pupil existingPupil = repository.findByAccountEmail(email).orElse(null);
        //Has account and pupil
        if (existingPupil != null) return existingPupil;
        if (accountService.isEmailFree(email)) return createDefaultPupilWithNewAccount(email);
        return createDefaultPupil(accountService.getAccountByEmail(email));

    }
    public Pupil createDefaultPupil(Account account) {
        Pupil pupil = new Pupil();
        Gender gender = genderService.findGenderByName(defaultGender);
        pupil.setName(defaultName);
        pupil.setSurname(defaultSecondName);
        pupil.setPatronymic(defaultPatronymic);
        pupil.setGender(gender);
        pupil.setAccount(account);
        return repository.save(pupil);
    }
    public Pupil createDefaultPupilWithNewAccount(String email) {
        AccountApiRegisterDTO registerDTO = new AccountApiRegisterDTO();

        // Setup pupil DTO
        PupilDTO pupilDTO = new PupilDTO();
        pupilDTO.setGender(defaultGender);
        pupilDTO.setName(defaultName);
        pupilDTO.setSurname(defaultSecondName);
        pupilDTO.setPatronymic(defaultPatronymic);
        registerDTO.setPupilDTO(pupilDTO);

        // Setup account DTO
        AccountRegisterRequestDTO accountDTO = new AccountRegisterRequestDTO();
        accountDTO.setEmail(email);
        accountDTO.setPassword(defaultPassword);
        registerDTO.setAccountRegisterRequestDTO(accountDTO);

        createWithAccount(registerDTO);
        return repository.findByAccountEmail(email)
                .orElseThrow(() -> new RuntimeException("Cannot find pupil by email: " + email));
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
    public Optional<Pupil> getPupilByAccountEmail(String email) {
        return repository.findByAccountEmail(email);
    }
}
