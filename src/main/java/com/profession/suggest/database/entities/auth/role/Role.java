package com.profession.suggest.database.entities.auth.role;

import com.profession.suggest.database.entities.auth.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString(exclude = "accounts")
@EqualsAndHashCode(exclude = "accounts")
@NoArgsConstructor
@AllArgsConstructor
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
