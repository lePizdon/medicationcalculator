package vet.goat.controller.payload.search;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.entity.payload.Medication;
import vet.goat.exceptions.NoSuchMedicationException;
import vet.goat.service.payload.MedicationServiceImpl;

import java.util.List;

@RestController("/catalogue/medication")
@RequiredArgsConstructor
public class MedicationSearchController {
    private final MedicationServiceImpl medicationService;

    @GetMapping("/{name}")
    public List<Medication> getMedicationByName(@PathVariable @Valid String name) {
        return medicationService.getMedicationByName(name);
    }

    @GetMapping("/{name}/{injectionType}")
    public ResponseEntity<Medication> getMedicationByFullParams (@PathVariable @Valid String name,
                                                                 @PathVariable @Valid String injectionType) {
        try {
            Medication medication = medicationService.getMedicationByFullParams(name, injectionType);
            return ResponseEntity.ok(medication);
        } catch (NoSuchMedicationException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
