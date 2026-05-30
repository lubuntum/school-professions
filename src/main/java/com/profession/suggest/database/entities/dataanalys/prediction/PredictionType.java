package com.profession.suggest.database.entities.dataanalys.prediction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prediction_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PredictionTypeEnum name;
}
