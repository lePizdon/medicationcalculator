package vet.goat.controller.payload.edit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.entity.payload.Medication;
import vet.goat.service.payload.MedicationServiceImpl;

@RestController("edit/medication")
@RequiredArgsConstructor
public class MedicationEditController {
    private final MedicationServiceImpl medicationService;

    @PostMapping("/{id}")
    public ResponseEntity<Medication> editMedication(@PathVariable Long id,
                                                     @RequestBody @Valid Medication medication) {
        medicationService.updateMedication(medication);
        return ResponseEntity.ok(medication);
    }

    @PostMapping("/create")
    public ResponseEntity<Medication> createMedication(@RequestBody @Valid Medication medication) {
        medicationService.create(medication);
        return ResponseEntity.ok(medication);
    }
}
