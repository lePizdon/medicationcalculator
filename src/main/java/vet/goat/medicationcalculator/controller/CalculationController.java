package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.exception.ActiveSubstanceNotPresented;
import vet.goat.medicationcalculator.service.CalculationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/calculate")
public class CalculationController {
    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/param")
    public ResponseEntity<Map<String, Double>> dosageByFullParams(@RequestParam String medicationName,
                                                                  @RequestParam String injectionType,
                                                                  @RequestParam String animalType,
                                                                  @RequestParam Double weight) {
        Map<String, Double> res = calculationService
                .calculateDosage(medicationName, injectionType, animalType, weight);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/active")
    public ResponseEntity<Map<String, Double>> getActiveDosageMlByFullParams(@RequestParam String medicationName,
                                                                             @RequestParam String animalType,
                                                                             @RequestParam String injectionType,
                                                                             @RequestParam Double weight) {
        Map<String, Double> result = new HashMap<>();
        try {
            result = calculationService.getActiveDosageMlByFullParams(medicationName, animalType,
                    injectionType, weight);
        } catch (ActiveSubstanceNotPresented e) {
            e.printStackTrace();
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
