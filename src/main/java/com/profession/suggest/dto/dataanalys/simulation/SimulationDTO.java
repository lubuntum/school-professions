package com.profession.suggest.dto.dataanalys.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationDTO {
    private String filePath;
    private LocalDateTime startSimulation;
    private LocalDateTime endSimulation;
    private LocalDateTime createdAt;
    private String email;
    private String simulationType;
    private String profession;

}
