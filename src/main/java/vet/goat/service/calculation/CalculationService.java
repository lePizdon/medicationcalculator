package vet.goat.service.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.dto.DosageDetailsDTO;
import vet.goat.dto.DosageRange;
import vet.goat.service.payload.DosageServiceImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
