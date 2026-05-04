package com.profession.suggest.database.entities.professions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Entity
@Table(name = "profession_sphere")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessionSphere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "professionSphere")
    private List<Profession> professions;
}
