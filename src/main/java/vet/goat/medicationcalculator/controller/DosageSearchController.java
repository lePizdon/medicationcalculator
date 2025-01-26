package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exceptions.NoSuchDosageException;
import vet.goat.medicationcalculator.service.DosageServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/search/dosage")
public class DosageSearchController {

    private final DosageServiceImpl dosageService;

    @Autowired
    DosageSearchController(DosageServiceImpl dosageService) {
        this.dosageService = dosageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dosage>> getAllDosages() {
        List<Dosage> result = dosageService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/by/params")
    public ResponseEntity<List<Dosage>> getDosageByFullParams(@RequestParam String medicationName,
                                                        @RequestParam(required = false) String animalType,
                                                        @RequestParam(required = false) String injectionType) {
        try {
            List<Dosage> dosage = dosageService.getDosageByFullParams(medicationName, animalType, injectionType);
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/by/name")
    public ResponseEntity<List<Dosage>> getDosagesByName(@RequestParam String name) {
        List<Dosage> resultList = dosageService.getDosagesByName(name);
        if (resultList.isEmpty()) {
            return ResponseEntity.status(402).build();
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/by/pair")
    public ResponseEntity<List<Dosage>> getDosagesByPair(@RequestParam String medicationName,
                                         @RequestParam String injectionType) {
        List<Dosage> resultList = dosageService.getDosagesByPair(medicationName, injectionType);
        if (resultList.isEmpty()) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/by/id")
    public ResponseEntity<Dosage> getDosageById(@RequestParam Long id) {
        try {
            Dosage dosage = dosageService.getDosageById(id).orElseThrow(() ->
                    new NoSuchDosageException("{dosage.get.fullparams.null}"));
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
