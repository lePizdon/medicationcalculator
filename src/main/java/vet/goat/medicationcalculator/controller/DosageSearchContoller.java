package vet.goat.medicationcalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.exceptions.NoSuchDosageException;
import vet.goat.medicationcalculator.service.DosageServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController("/dosage/search")
public class DosageSearchContoller {
    private final DosageServiceImpl dosageService;

    @GetMapping("/fullparameters/{medicationName}")
    public ResponseEntity<Dosage> getDosageByFullParams(@Param("medicationName") String medicationName,
                                                        String animalType, String injectionType) {
        try {
            Dosage dosage = dosageService.getDosageByFullParams(medicationName, animalType, injectionType);
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{name}")
    public List<Dosage> getDosagesByName(@PathVariable("name") String name) {
        return dosageService.getDosagesByName(name);
    }

    @GetMapping("/pair/{medicationName}")
    public List<Dosage> getDosagesByPair(@PathVariable("medicationName") String medicationName, String injectionType) {
        return dosageService.getDosagesByPair(medicationName, injectionType);
    }

    @GetMapping("/{dosageId}")
    public ResponseEntity<Dosage> getDosageById(@PathVariable("dosageId") Long id) {
        Dosage dosage = null;
        try {
            dosage = dosageService.getDosageById(id).orElseThrow(() ->
                    new NoSuchDosageException("{dosage.get.fullparams.null}"));
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
