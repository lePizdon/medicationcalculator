package vet.goat.controller.payload.edit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.entity.payload.Dosage;
import vet.goat.service.payload.DosageServiceImpl;

@RestController("/edit/dosage")
@RequiredArgsConstructor
public class DosageEditController {
    private final DosageServiceImpl dosageService;

    @PostMapping("/{id}")
    public ResponseEntity<Dosage> editDosage(@RequestBody @Valid Dosage editable) {
        dosageService.updateDosage(editable);
        return ResponseEntity.ok(editable);
    }

    @PostMapping("/create")
    public ResponseEntity<Dosage> createDosage(@RequestBody Dosage dosage) {
        dosageService.create(dosage);
        return ResponseEntity.ok(dosage);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteDosage(@RequestBody Dosage dosage) {
        dosageService.deleteDosage(dosage);
        return ResponseEntity.ok().build();
    }
}
