package com.profession.suggest.controllers.pupil;

import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pupils")
public class PupilController {

    private final PupilService pupilService;

    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping("/get")
    public ResponseEntity<Page<PupilResponseDTO>> getAllValidPupilWithAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String school,
            @RequestParam(required = false) String email) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
    return ResponseEntity.ok(pupilService.getPupilsData(pageable));
    }
}
