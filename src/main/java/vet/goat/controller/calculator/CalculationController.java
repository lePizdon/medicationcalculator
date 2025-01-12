package vet.goat.controller.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.exceptions.ActiveSubstanceNotPresented;
import vet.goat.service.calculation.CalculationService;

import java.util.HashMap;
import java.util.Map;

@RestController("/calculate")
@RequiredArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @GetMapping("/dosage/{medicationName}")
    public ResponseEntity<Map<String,Double>> dosageByFullParams(@PathVariable String medicationName,
                                                                 String animalType, String injectionType,
                                                                 Double weight){
        Map<String, Double> res  = calculationService
                .calculateDosage(medicationName, animalType, injectionType, weight);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/dosage/active/{medicationName}")
    public ResponseEntity<Map<String, Double>> getActiveDosageMlByFullParams(@PathVariable String medicationName,
                                                              String animalType, String injectionType,
                                                              Double weight){
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
