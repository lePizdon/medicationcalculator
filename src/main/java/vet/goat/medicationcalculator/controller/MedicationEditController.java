package vet.goat.medicationcalculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.service.MedicationServiceImpl;

@RestController("/medication/edit")
@RequiredArgsConstructor
public class MedicationEditController {
    private final MedicationServiceImpl medicationService;

    @PostMapping("/{medicationId}")
    public ResponseEntity<Medication> editMedication(@PathVariable("medicationId") Long id,
                                                     @RequestBody @Valid Medication medication) {
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
