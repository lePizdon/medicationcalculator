package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.service.MedicationServiceImpl;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(("/medication/search"))
public class MedicationSearchController {
    private MedicationServiceImpl medicationService;

    @Autowired
    public MedicationSearchController(MedicationServiceImpl medicationService) {
        this.medicationService = medicationService;
    }

        @GetMapping("/{medicationId}")
    public ResponseEntity<Medication> getMedicationsById(@PathVariable("searchableMedicationId") Long id) {
        Medication result = medicationService.getMedicationById(id);
        if (result == null) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Medication>> getMedicationByName(@PathVariable("name") String name) {
        List<Medication> resultList = medicationService.getMedicationByName(name);
        if (resultList.isEmpty()) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(resultList);
    }
}
