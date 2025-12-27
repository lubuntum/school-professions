package com.profession.suggest.controllers.pupil;

import com.profession.suggest.database.services.pupil.PupilService;
import com.profession.suggest.dto.pupil.PupilDTO;
import com.profession.suggest.dto.pupil.PupilResponseDTO;
import org.apache.tomcat.util.http.parser.Authorization;
import org.osgi.annotation.bundle.Header;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * TODO
 * 1. get pupil values by token +
 * 2. get all pupil tests data by token
 * */
@RestController
@RequestMapping("/api/pupils")
public class PupilController {

    private final PupilService pupilService;

    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping()
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
    @GetMapping("/pupil-by-id")
    public ResponseEntity<PupilResponseDTO> getPupilData(@RequestAttribute("id") Long id) {
        return ResponseEntity.ok(pupilService.getPupilData(id));
    }
}
