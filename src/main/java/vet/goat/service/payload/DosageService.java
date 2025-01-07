package vet.goat.service.payload;

import org.springframework.stereotype.Service;
import vet.goat.entity.payload.Dosage;
import vet.goat.exceptions.NoSuchDosageException;

import java.util.List;
import java.util.Optional;

@Service
public interface DosageService {
    Dosage getDosageByFullParams(String medicationName, String animalType, String injectionType) throws NoSuchDosageException;

    List<Dosage> getDosagesByName(String name);

    List<Dosage> getDosagesByPair(String medicationName, String injectionType);

    void updateDosage(Dosage editable);

    void create(Dosage dosage);

    Optional<Dosage> getDosageById(Long id);

    void deleteDosage(Dosage dosage);
}
