package vet.goat.medicationcalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.service.DosageServiceImpl;

@RestController("/dosage/edit")
@RequiredArgsConstructor
public class DosageEditController {
    private final DosageServiceImpl dosageService;

    @PostMapping("/{dosageId}")
    public ResponseEntity<Dosage> editDosage(@PathVariable("dosageId") Long id, @RequestBody Dosage editable) {
        dosageService.updateDosage(editable);
        return ResponseEntity.ok(editable);
    }

    @PostMapping("/create/{dosageIDid}")
    public ResponseEntity<Dosage> createDosage(@PathVariable("dosageID") Long id, @RequestBody Dosage dosage) {
        dosageService.create(dosage);
        return ResponseEntity.ok(dosage);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteDosage(@RequestBody Dosage dosage) {
        dosageService.deleteDosage(dosage);
        return ResponseEntity.ok().build();
    }
}
