package com.profession.suggest.database.services.dataanalys.prediction;

import com.profession.suggest.database.entities.dataanalys.prediction.Prediction;
import com.profession.suggest.database.entities.dataanalys.prediction.PredictionType;
import com.profession.suggest.database.entities.users.pupil.Pupil;
import com.profession.suggest.database.repositories.dataanalys.prediction.PredictionRepository;
import com.profession.suggest.database.repositories.dataanalys.prediction.PredictionTypeRepository;
import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.dataanalys.prediction.PredictionDTO;
import com.profession.suggest.dto.dataanalys.prediction.PredictionMapper;
import com.profession.suggest.services.files.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PredictionService {
    private final PredictionRepository repository;
    private final PredictionMapper mapper;
    private final PredictionTypeService predictionTypeService;
    private final PupilService pupilService;
    private final FileStorageService fileStorageService;

    public PredictionDTO createPrediction(PredictionDTO dto, MultipartFile file) throws Exception {
        if (dto == null)
            throw new IllegalArgumentException("Prediction DTO cannot be null");
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("File cannot be null or empty");
        if (dto.getPupilId() == null || dto.getPupilId() <= 0)
            throw new IllegalArgumentException("Valid pupil ID is required");
        if (dto.getPredictionType() == null)
            throw new IllegalArgumentException("Prediction type is required");
        if (file.getSize() > 10 * 1024 * 1024)
            throw new IllegalArgumentException("File size more then 10MB");

        PredictionType type = predictionTypeService.getByName(dto.getPredictionType());
        Prediction prediction = mapper.fromDTO(dto, type);
        Pupil pupil = pupilService.getPupilById(dto.getPupilId());

        if (pupil == null) throw new EntityNotFoundException(
                String.format("Pupil not found with id: %d", dto.getPupilId()));
        if (type == null) throw new EntityNotFoundException(
                String.format("Prediction type not found: %s", dto.getPredictionType()));

        prediction.setFilePath(fileStorageService.saveFile(file, "predictions", true));
        prediction.setPupil(pupil);
        return mapper.toDTO(repository.save(prediction));
    }
}
