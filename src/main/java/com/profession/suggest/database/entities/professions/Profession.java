package com.profession.suggest.database.entities.professions;

import com.profession.suggest.database.entities.dataanalys.simulation.Simulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "profession")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "profession")
    private List<Simulation> simulations;
}
