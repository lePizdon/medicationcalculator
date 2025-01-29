package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Medication;
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

    @GetMapping("/{medicationId}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable("medicationId") Long medicationId) {
        Medication result = medicationService.getMedicationById(medicationId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{medicationName}")
    public ResponseEntity<List<Medication>> getMedicationByName(@PathVariable("medicationName")
                                                                    String medicationName) {
        List<Medication> result = medicationService.getMedicationByName(medicationName);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
}
