package com.profession.suggest.database.services.auth.role;

import com.profession.suggest.database.entities.auth.role.Role;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.repositories.auth.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public Role findByName(RoleEnum name) {
        return repository.findByName(name);
    }
}
