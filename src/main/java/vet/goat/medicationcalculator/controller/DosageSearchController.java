package vet.goat.medicationcalculator.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.exception.NoSuchDosageException;
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
    @GetMapping("/params")
    public ResponseEntity<List<Dosage>> getDosageByFullParams(@RequestParam String medicationName,
                                                        @RequestParam(required = false) String animalType,
                                                        @RequestParam(required = false) String injectionType) {
        try {
            List<Dosage> dosage = dosageService.getDosageByFullParams(medicationName, animalType, injectionType);
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            LoggerFactory.getLogger(DosageSearchController.class).error("{}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/name/{dosageName}")
    public ResponseEntity<List<Dosage>> getDosagesByName(@PathVariable("dosageName") String dosageName) {
        List<Dosage> resultList = dosageService.getDosagesByName(dosageName);
        if (resultList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/pair")
    public ResponseEntity<List<Dosage>> getDosagesByPair(@RequestParam String medicationName,
                                         @RequestParam String injectionType) {
        List<Dosage> resultList = dosageService.getDosagesByPair(medicationName, injectionType);
        if (resultList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/id/{dosageId}")
    public ResponseEntity<Dosage> getDosageById(@PathVariable("dosageId") Long dosageId) {
        try {
            Dosage dosage = dosageService.getDosageById(dosageId).orElseThrow(() ->
                    new NoSuchDosageException("{dosage.get.fullparams.null}"));
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            LoggerFactory.getLogger(DosageSearchController.class).error("{}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
