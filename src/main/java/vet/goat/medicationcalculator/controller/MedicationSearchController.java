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

    @GetMapping("/id")
    public ResponseEntity<Medication> getMedicationById(@RequestParam Long id) {
        Medication result = medicationService.getMedicationById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Medication>> getMedicationByName(@RequestParam String name) {
        List<Medication> result = medicationService.getMedicationByName(name);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
}
