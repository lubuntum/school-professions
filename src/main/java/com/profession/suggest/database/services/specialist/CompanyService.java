package com.profession.suggest.database.services.specialist;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.users.specialist.Company;
import com.profession.suggest.database.entities.users.specialist.Specialist;
import com.profession.suggest.database.repositories.specialist.CompanyRepository;
import com.profession.suggest.database.services.auth.AccountService;
import com.profession.suggest.dto.auth.AccountRegisterRequestDTO;
import com.profession.suggest.dto.company.*;
import com.profession.suggest.dto.specialist.SpecialistDTO;
import com.profession.suggest.dto.specialist.SpecialistMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;
    private final AccountService accountService;
    private final SpecialistService specialistService;

    private final CreateEmployeeMapper createEmployeeMapper;
    private final SpecialistMapper specialistMapper;
    private final CompanyMapper companyMapper;
    private final EntityManager entityManager;

    /**
     * Get company by account ID
     * Checks if user is a specialist and has a company
     */
    public CompanyDTO getCompanyByAccountId(Long accountId) throws AccountNotFoundException {
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

        return companyMapper.toDTO(company);
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

    /**
     * Get all companies with ALL specialists (for Admin)
     * More complete view
     */
    public List<CompanyWithEmployeesDTO> getCompaniesWithEmployees() {
        List<Company> companies = repository.findAll();

        return companies.stream()
                .map(company -> {
                    /*if there is any employees in company parse them, else just empty array */
                    List<Employee> companyEmployees = company.getSpecialists() != null
                            ? company.getSpecialists().stream()
                            .map(specialist -> {
                                Account account = specialist.getAccount();
                                boolean isHR = account.getRoles().stream()
                                        .anyMatch(role -> role.getName() == RoleEnum.HR);

                                return Employee.builder()
                                        .id(specialist.getId())
                                        .fullName(specialist.getFullName())
                                        .email(account.getEmail())
                                        .role(isHR ? "HR" : "SPECIALIST")
                                        .build();
                            })
                            .collect(Collectors.toList())
                            : new ArrayList<>();

                    return CompanyWithEmployeesDTO.builder()
                            .id(company.getId())
                            .name(company.getName())
                            .inn(company.getInn())
                            .ogrn(company.getOgrn())
                            .address(company.getAddress())
                            .phone(company.getPhone())
                            .email(company.getEmail())
                            .employees(companyEmployees)
                            .employeesCount(companyEmployees.size())
                            .build();
                })
                .collect(Collectors.toList());
    }

    // ... rest of existing methods
    @Transactional
    public CreateEmployeeResponse createEmployeeForCompany(CreateEmployeeRequest request) throws BadRequestException, AccountNotFoundException {

        // 2. Find Company
        Company company = repository.findByName(request.getCompanyName());
        if (company == null) {
            throw new BadRequestException("Company not found: " + request.getCompanyName());
        }
        RoleEnum mainRole = request.getRole() == AllowedRole.HR
                ? RoleEnum.HR
                : RoleEnum.SPECIALIST;
        RoleEnum employeeRole = RoleEnum.EMPLOYEE;
        // 3. Create Account with role
        AccountRegisterRequestDTO accountDTO = new AccountRegisterRequestDTO();
        accountDTO.setEmail(request.getEmail());
        accountDTO.setPassword(request.getPassword());
        Account account = accountService.registration(accountDTO, mainRole, employeeRole);
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
        entityManager.refresh(account);
        return createEmployeeMapper.toDTO(account);//with refreshed account data
    }

    public Company createCompany(CompanyDTO companyDTO) {
        if (companyDTO.getName() == null || companyDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name is required");
        }
        if (repository.findByName(companyDTO.getName()) != null) {
            throw new IllegalArgumentException("Company already exists: " + companyDTO.getName());
        }
        Company company = new Company();
        company.setName(companyDTO.getName());  // FIXED: Set name
        company.setInn(companyDTO.getInn());
        company.setOgrn(companyDTO.getOgrn());
        company.setEmail(companyDTO.getEmail());
        company.setAddress(companyDTO.getAddress());
        company.setPhone(companyDTO.getPhone());
        return repository.save(company);  // FIXED: Save immediately
    }
}
