package com.profession.suggest.database.entities.dataanalys.simulation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "simulation_data_source")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationDataSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "simulationDataSource")
    private List<Simulation> simulations;
}
