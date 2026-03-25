package com.profession.suggest.dto.dataanalys.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResponseDTO{
    private Long id;
    private SimulationDTO simulation;
}
