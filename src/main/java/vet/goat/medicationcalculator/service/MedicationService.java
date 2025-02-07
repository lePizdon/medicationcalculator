package vet.goat.medicationcalculator.service;

import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exception.NoSuchMedicationException;

import java.util.List;
import java.util.Optional;

@Service
public interface MedicationService{
    List<Medication> getMedicationByName(String name);



    void updateMedication(Medication medication);

    void create(Medication medication);

    Medication getMedicationById(Long id);

    List<Medication> findAll();

    List<Medication> getMedicationByParams(String medicationName, String injectionType) throws NoSuchMedicationException;
}
