package vet.goat.medicationcalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.service.DosageServiceImpl;

@RestController
@RequestMapping("api/edit/dosage")
public class DosageEditController {
    private  DosageServiceImpl dosageService;

    @Autowired
    public DosageEditController(DosageServiceImpl dosageService) {
        this.dosageService = dosageService;
    }

    @PostMapping()
    public ResponseEntity<Dosage> editDosage(@RequestBody Dosage editable) {
        dosageService.updateDosage(editable);
        return ResponseEntity.ok(editable);
    }

    @PostMapping("/create")
    public ResponseEntity<Dosage> createDosage(@RequestBody Dosage dosage) {
        dosageService.create(dosage);
        return ResponseEntity.ok(dosage);
    }


    @DeleteMapping("/delete/single/whole")
    public ResponseEntity<Void> deleteDosage(@RequestBody Dosage dosage) {
        dosageService.deleteDosage(dosage);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/single/id")
    public ResponseEntity<Void> deleteDosageById(@RequestParam Long id) {
        dosageService.deleteDosageById(id);
        return ResponseEntity.ok().build();
    }
}
