package vet.goat.medicationcalculator.service;

import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.dto.DosageRange;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exceptions.NoSuchDosageException;

import java.util.List;
import java.util.Optional;

@Service
public interface DosageService {
    List<Dosage> getDosageByFullParams(String medicationName, String animalType, String injectionType) throws NoSuchDosageException;

    List<Dosage> getDosagesByName(String name);

    List<Dosage> getDosagesByPair(String medicationName, String injectionType);

    void updateDosage(Dosage editable);

    void create(Dosage dosage);

    Optional<Dosage> getDosageById(Long id);

    void deleteDosage(Dosage dosage);


    DosageRange getDosageValue(String medicationName, String animalType, String injectionType);

    Optional<Double> getActiveSubstanceByFullParams(String medicationName, String animalType, String injectionType);

    List<Dosage> findAll();

    void deleteDosageById(Long id);
}
