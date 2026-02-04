package com.profession.suggest.database.entities.pupil.subject.profile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "selection_probability_level")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProbabilityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "level", nullable = false, unique = true)
    private String level;
}
