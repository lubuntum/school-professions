package com.profession.suggest.database.entities.dataanalys.simulation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
* insert into simulation_type (name) values ('VR'),('BFB');
* */
@Entity
@Table(name = "simulation_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "simulationType")
    private List<Simulation> simulations;
}
