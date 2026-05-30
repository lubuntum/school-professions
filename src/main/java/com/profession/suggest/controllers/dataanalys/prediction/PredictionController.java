package com.profession.suggest.controllers.dataanalys.prediction;

import com.profession.suggest.database.services.dataanalys.prediction.PredictionService;
import com.profession.suggest.dto.dataanalys.prediction.PredictionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createPrediction(@RequestPart("prediction") PredictionDTO predictionDTO,
                                                          @RequestPart("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(predictionService.createPrediction(predictionDTO, file));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Please check all required parameters, cant save prediction");
        }
    }
}
