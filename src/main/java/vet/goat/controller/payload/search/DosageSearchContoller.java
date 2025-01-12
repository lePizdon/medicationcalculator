package vet.goat.controller.payload.search;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.entity.payload.Dosage;
import vet.goat.exceptions.NoSuchDosageException;
import vet.goat.service.payload.DosageServiceImpl;

import java.util.List;

@RestController("/catalogue/dosage")
@RequiredArgsConstructor
public class DosageSearchContoller {
    private final DosageServiceImpl dosageService;

    @GetMapping("/fullparameters/{name}")
    public ResponseEntity<Dosage> getDosageByFullParams(@Param("name") String medicationName, String animalType,
                                                        @Valid String injectionType) {
        try {
            Dosage dosage = dosageService.getDosageByFullParams(medicationName,animalType,injectionType);
            return ResponseEntity.ok(dosage);
        } catch (NoSuchDosageException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{name}")
    public List<Dosage> getDosagesByName(@Param("name") String name) {
        return dosageService.getDosagesByName(name);
    }

    @GetMapping("/pair/{name}")
    public List<Dosage> getDosagesByPair(@Param("name") String medicationName, String injectionType) {
        return dosageService.getDosagesByPair(medicationName, injectionType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dosage> getDosageById(@PathVariable Long id) {
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
