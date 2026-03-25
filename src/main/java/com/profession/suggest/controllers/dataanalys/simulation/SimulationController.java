package com.profession.suggest.controllers.dataanalys.simulation;

import com.profession.suggest.configuration.security.annotation.HasRole;
import com.profession.suggest.database.entities.auth.role.RoleEnum;
import com.profession.suggest.database.entities.dataanalys.simulation.Simulation;
import com.profession.suggest.database.services.dataanalys.simulation.SimulationService;
import com.profession.suggest.dto.dataanalys.simulation.SimulationDTO;
import com.profession.suggest.dto.dataanalys.simulation.SimulationMapper;
import com.profession.suggest.dto.dataanalys.simulation.SimulationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/simulations")
public class SimulationController {
    private final SimulationService simulationService;
    private final SimulationMapper simulationMapper;

    public SimulationController(SimulationService simulationService, SimulationMapper simulationMapper) {
        this.simulationService = simulationService;
        this.simulationMapper = simulationMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<SimulationDTO> createSimulation(@RequestPart("metadata") SimulationDTO simulationDTO, @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(simulationMapper
                .toDTO(simulationService
                        .createSimulation(simulationDTO, file), simulationDTO.getEmail()));
    }
    @HasRole(RoleEnum.ADMIN)
    @GetMapping
    public ResponseEntity<Page<SimulationResponseDTO>> getSimulations(@RequestParam(required = false) String email,
                                                                      @RequestParam(required = false) LocalDateTime startSimulation,
                                                                      @RequestParam(required = false) LocalDateTime endSimulation,
                                                                      @RequestParam(required = false) String simulationType,
                                                                      @RequestParam(required = false) String profession,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size,
                                                                      @RequestParam(defaultValue = "createdAt") String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString("desc"), sortBy));
        Page<Simulation> simulations = simulationService.findByFilters(email,
                startSimulation, endSimulation,
                simulationType, profession,
                pageable);
        return ResponseEntity.ok(
                simulations.map(s -> simulationMapper.toResponseDTO(s, s.getPupil().getAccount().getEmail())));
    }
}
