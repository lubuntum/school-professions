package com.profession.suggest.database.services.dataanalys.simulation;

import com.profession.suggest.database.entities.dataanalys.simulation.Simulation;
import com.profession.suggest.database.entities.dataanalys.simulation.SimulationType;
import com.profession.suggest.database.entities.professions.Profession;
import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.repositories.dataanalys.simulation.SimulationRepository;
import com.profession.suggest.database.services.profession.ProfessionService;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.simulation.SimulationDTO;
import com.profession.suggest.services.files.FileStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class SimulationService {
    private final SimulationRepository repository;
    private final SimulationTypeService simulationTypeService;
    private final ProfessionService professionService;
    private final PupilService pupilService;
    private final FileStorageService fileStorageService;
    /* TODO
    * simulation can be created even if account with email doesn't exists
    * (register account with default password and default pupil and continue)
    * */
    @Transactional
    public Simulation createSimulation(SimulationDTO simulationDTO, MultipartFile file) {
        try {

            Simulation simulation = new Simulation();
            SimulationType simulationType = simulationTypeService.getByName(simulationDTO.getSimulationType());
            Profession profession = professionService.getProfessionByName(simulationDTO.getProfession());
            Pupil pupil = pupilService
                    .getPupilByAccountEmail(simulationDTO.getEmail())
                    .orElse(pupilService.createWithDefaults(simulationDTO.getEmail()));
            //if there is no pupil found by account email,
            // that means need to create Account with email and Pupil
            simulation.setSimulationType(simulationType);
            simulation.setProfession(profession);
            simulation.setStartSimulation(simulationDTO.getStartSimulation());
            simulation.setEndSimulation(simulationDTO.getEndSimulation());
            simulation.setPupil(pupil);
            simulation.setFilePath(fileStorageService.saveFile(file, "simulations", true));

            return repository.save(simulation);
        } catch (IOException e) {
            log.error("Error while saving simulation file for email {}", simulationDTO.getEmail() ,e);
            throw new RuntimeException("Failed to save a file", e);
        } catch (Exception e) {
            log.error("Error occurred while creating simulation for email: {}", simulationDTO.getEmail() ,e);
            throw new RuntimeException("Failed to create simulation for email",  e);
        }
    }
    public Page<Simulation> findByFilters(String email,
                                          LocalDateTime startSimulation, LocalDateTime endSimulation,
                                          String simulationType, String profession, Pageable pageable) {
        Specification<Simulation> spec = ((root, query, cb) -> cb.conjunction());
        if (email != null && !email.isEmpty())
            spec = spec.and(((root, query, cb) ->
                    cb.equal(root.get("pupil").get("account").get("email"), email)));
        if (startSimulation != null)
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("startSimulation"), startSimulation));
        if (endSimulation != null)
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("endSimulation"), endSimulation));
        if (simulationType != null && !simulationType.isEmpty())
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("simulationType").get("name"), simulationType));
        if (profession != null && !profession.isEmpty())
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("profession").get("name"), profession));
        return repository.findAll(spec, pageable);

    }
}
