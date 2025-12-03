package com.profession.suggest.database.entities.account.role;

import com.profession.suggest.database.entities.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    RoleEnum name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();
}
