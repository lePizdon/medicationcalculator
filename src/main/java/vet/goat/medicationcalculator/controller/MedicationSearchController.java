package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.service.MedicationServiceImpl;

import java.util.List;

@RestController
@RequestMapping(("/medication"))
public class MedicationSearchController {
    private MedicationServiceImpl medicationService;

    @Autowired
    public MedicationSearchController(MedicationServiceImpl medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/zhopi/{id}")
    public ResponseEntity<Medication> getMedicationsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(medicationService.getMedicationById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Medication>> getMedicationByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(medicationService.getMedicationByName(name));
    }
}
