package vet.goat.medicationcalculator.service;

import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exceptions.NoSuchMedicationException;

import java.util.List;

@Service
public interface MedicationService{
    List<Medication> getMedicationByName(String name);

    Medication getMedicationByFullParams(String name, String injectionType) throws NoSuchMedicationException;

    void updateMedication(Medication medication);

    void create(Medication medication);

    Medication getMedicationById(Long id);
}
