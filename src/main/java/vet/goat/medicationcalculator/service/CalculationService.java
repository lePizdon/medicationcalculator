package vet.goat.medicationcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.dto.DosageRange;
import vet.goat.medicationcalculator.exception.ActiveSubstanceNotPresented;

import java.util.*;

@Service
public class CalculationService {
    private final DosageServiceImpl dosageService;

    @Autowired
    public CalculationService(DosageServiceImpl dosageService) {
        this.dosageService = dosageService;
    }
    public Map<String, Double> calculateDosage(String medicationName, String injectionType, String animalType,
                                               Double weight) {
        DosageRange range = dosageService.getDosageValue(medicationName, injectionType, animalType);
        Map<String, Double> result = new HashMap<>();
        result.put("start_value", range.startValue()*weight);
        if (range.endValue() != null) {
            result.put("end_value", range.endValue()*weight);
        }
        return result;
    }

    public Map<String, Double> getActiveDosageMlByFullParams(String medicationName, String animalType,
                                                             String injectionType, Double weight)
            throws ActiveSubstanceNotPresented {
        Double dbActiveSubstanceValue = dosageService
                .getActiveSubstanceByFullParams(medicationName, animalType, injectionType)
                .orElseThrow(() -> new ActiveSubstanceNotPresented("{calculation.active.substance.not.presented}"));

        DosageRange dbValue = dosageService.getDosageValue(medicationName, animalType, injectionType);

        Map<String,Double> result = new HashMap<>();

        result.put("start_value", (dbValue.startValue()*weight)/dbActiveSubstanceValue);
        if (dbValue.endValue() != null) {
            result.put("end_value", (dbValue.endValue()*weight)/dbActiveSubstanceValue);
        }
        return result;
    }
}
