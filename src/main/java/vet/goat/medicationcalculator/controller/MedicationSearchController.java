package vet.goat.medicationcalculator.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exception.NoSuchMedicationException;
import vet.goat.medicationcalculator.service.MedicationServiceImpl;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/search/medication")
public class MedicationSearchController {
    private final MedicationServiceImpl medicationService;

    @Autowired
    public MedicationSearchController(MedicationServiceImpl medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medication>> findAll() {
        List<Medication> medications = medicationService.findAll();
        if (medications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/id/{medicationId}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable("medicationId") Long medicationId) {
        Medication result = medicationService.getMedicationById(medicationId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{medicationName}")
    public ResponseEntity<List<Medication>> getMedicationByName(@PathVariable("medicationName") String medicationName) {
        List<Medication> result = medicationService.getMedicationByName(medicationName);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/params")
    public ResponseEntity<List<Medication>> getMedicationByParams(@RequestParam(required = false) String medicationName,
                                                            @RequestParam(required = false) String injectionType) {
        try {
            List<Medication> result = medicationService.getMedicationByParams(medicationName, injectionType);
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (NoSuchMedicationException e) {
            LoggerFactory.getLogger(MedicationSearchController.class);
            return ResponseEntity.noContent().build();
        }
    }
}
