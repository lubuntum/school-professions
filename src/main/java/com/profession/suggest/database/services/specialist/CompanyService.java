package com.profession.suggest.database.services.specialist;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.users.specialist.Company;
import com.profession.suggest.database.entities.users.specialist.Specialist;
import com.profession.suggest.database.repositories.specialist.CompanyRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.company.CreateHRRequest;
import com.profession.suggest.dto.company.HRMapper;
import com.profession.suggest.dto.company.HRResponse;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import com.profession.suggest.dto.specialist.SpecialistMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;
    private final AccountService accountService;
    private final SpecialistService specialistService;

    private final HRMapper hrMapper;
    private final SpecialistMapper specialistMapper;

    /**
     * Get company by account ID
     * Checks if user is a specialist and has a company
     */
    public Company getCompanyByAccountId(Long accountId) throws AccountNotFoundException {
        Account account = accountService.getAccountById(accountId);

        // Check if user is a specialist
        Specialist specialist = account.getSpecialist();
        if (specialist == null) {
            throw new IllegalArgumentException("User is not a specialist");
        }

        // Check if user has a company
        Company company = specialist.getCompany();
        if (company == null) {
            throw new IllegalArgumentException("No company assigned to this user");
        }

        return company;
    }

    /**
     * Get all specialists in a company
     */
    public List<Specialist> getCompanySpecialists(Long companyId) {
        Company company = repository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        return company.getSpecialists();
    }
    public List<Specialist> getSpecialistsByHRAccountId(Long accountId) throws AccountNotFoundException {
        // 1. Get account
        Account account = accountService.getAccountById(accountId);

        // 2. Check if user is a specialist
        Specialist hrSpecialist = account.getSpecialist();
        if (hrSpecialist == null) {
            throw new IllegalArgumentException("User is not a specialist");
        }

        // 3. Check if user is HR (optional but recommended)
        boolean isHR = account.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleEnum.HR);
        if (!isHR) {
            throw new IllegalArgumentException("User is not an HR");
        }

        // 4. Get company
        Company company = hrSpecialist.getCompany();
        if (company == null) {
            throw new IllegalArgumentException("HR has no company assigned");
        }

        // 5. Return all specialists in the company (including the HR)
        return company.getSpecialists();
    }
    /**
     * Get all specialists in a company with their account info
     * Returns DTOs instead of entities for better control
     */
    public List<SpecialistDTO> getSpecialistDTOsByHRAccountId(Long accountId) throws AccountNotFoundException {
        List<Specialist> specialists = getSpecialistsByHRAccountId(accountId);
        return specialists.stream()
                .map(specialist -> specialistMapper.toDTO(specialist, specialist.getAccount()))
                .collect(Collectors.toList());
    }

    public List<HRResponse> getHRsWithCompanies() {
        Set<Account> accounts = accountService.getAccountsByRole(RoleEnum.HR);
        return accounts.stream()
                .map(hrMapper::toDTO)
                .collect(Collectors.toList());

    }
    @Transactional
    public HRResponse createHRWithCompany(CreateHRRequest request) throws BadRequestException {
        // 1. Validate email
        if (!accountService.isEmailFree(request.getEmail())) {
            throw new BadRequestException("Email already in use: " + request.getEmail());
        }

        // 2. Find or create Company (SAVE IT IMMEDIATELY)
        Company company = repository.findByName(request.getCompanyName());
        if (company == null) {
            company = createCompany(request);  // Returns saved company
        }

        // 3. Create Account with HR role
        AccountRegisterRequestDTO accountDTO = new AccountRegisterRequestDTO();
        accountDTO.setEmail(request.getEmail());
        accountDTO.setPassword(request.getPassword());
        Account account = accountService.registration(accountDTO, RoleEnum.HR);

        // 4. Create Specialist (HR profile) and link to company
        Specialist specialist = new Specialist();
        specialist.setAccount(account);
        specialist.setCompany(company);  // Link to company
        specialist.setName(request.getName());
        specialist.setSurname(request.getSurname());
        specialist.setPatronymic(request.getPatronymic());

        // 5. Save specialist
        Specialist savedSpecialist = specialistService.save(specialist);

        // 6. Add specialist to company's list (optional, for consistency)
        if (company.getSpecialists() == null) {
            company.setSpecialists(new ArrayList<>());
        }
        company.getSpecialists().add(savedSpecialist);
        repository.save(company);  // Save company with updated specialists list

        return hrMapper.toDTO(account);
    }

    private Company createCompany(CreateHRRequest request) {
        Company company = new Company();
        company.setName(request.getCompanyName());  // FIXED: Set name
        company.setInn(request.getCompanyInn());
        company.setOgrn(request.getCompanyOgrn());
        company.setEmail(request.getCompanyEmail());
        company.setAddress(request.getCompanyAddress());
        company.setPhone(request.getCompanyPhone());
        return repository.save(company);  // FIXED: Save immediately
    }
}
