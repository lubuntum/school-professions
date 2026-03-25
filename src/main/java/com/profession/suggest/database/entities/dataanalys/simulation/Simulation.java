package com.profession.suggest.database.entities.dataanalys.simulation;

import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.entities.pupil.Pupil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * insert into profession (name) values ('Взрывник'),('Горный инженер'),('Горный мастер'),('Водитель белаза'),('Горноспасатель');
 * */
@Entity
@Table(name = "simulation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "start_sim")
    private LocalDateTime startSimulation;
    @Column(name = "end_sim")
    private LocalDateTime endSimulation;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pupil_id")
    private Pupil pupil;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulation_type_id")
    private SimulationType simulationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private Profession profession;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
