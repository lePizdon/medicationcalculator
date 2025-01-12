package vet.goat.service.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.dto.DosageRange;
import vet.goat.exceptions.ActiveSubstanceNotPresented;
import vet.goat.service.payload.DosageServiceImpl;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CalculationService {
    private final DosageServiceImpl dosageService;

    public Map<String, Double> calculateDosage(String medicationName, String animalType, String injectionType,
                                               Double weight) {
        DosageRange range = dosageService.getDosageValue(medicationName, animalType, injectionType);
        Map<String, Double> result = new HashMap<>();
        result.put("start_value", range.startValue()*weight);
        if (range.endValue() != null) {
            result.put("end_value", range.endValue()*weight);
        }
        return result;
    }

    public Map<String, Double> getActiveDosageMlByFullParams(String medicationName, String animalType, String injectionType,
                                                Double weight) throws ActiveSubstanceNotPresented {
        Double dbActiveSubstanceValue = dosageService.getActiveSubstanceByFullParams(medicationName, animalType, injectionType)
                .orElseThrow(() -> new ActiveSubstanceNotPresented("{calculation.activesubstance.notpresented}"));

        DosageRange dbValue = dosageService.getDosageValue(medicationName, animalType, injectionType);

        Map<String,Double> result = new HashMap<>();

        result.put("start_value", (dbValue.startValue()*weight)/dbActiveSubstanceValue);
        if (dbValue.endValue() != null) {
            result.put("end_value", (dbValue.endValue()*weight)/dbActiveSubstanceValue);
        }
        return result;
    }
}
