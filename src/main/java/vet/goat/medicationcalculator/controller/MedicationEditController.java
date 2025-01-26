package vet.goat.medicationcalculator.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.service.MedicationServiceImpl;

@RestController
@RequestMapping("api/edit/medication")
public class MedicationEditController {
    private final MedicationServiceImpl medicationService;

    @Autowired
    MedicationEditController(MedicationServiceImpl medicationService) {
        this.medicationService = medicationService;
    }
    @PostMapping
    public ResponseEntity<Medication> editMedication(@RequestBody @Valid Medication medication) {
        medicationService.updateMedication(medication);
        return ResponseEntity.ok(medication);
    }

    @PostMapping("/create")
    public ResponseEntity<Medication> createMedication(@RequestBody @Valid Medication medication) {
        medicationService.create(medication);
        return ResponseEntity.ok(medication);
    }

    @PostMapping("/add")
    public ResponseEntity<Medication> addMedication(@RequestBody @Valid Medication medication) {
        medicationService.updateMedication(medication);
        return ResponseEntity.ok(medication);
    }
}
