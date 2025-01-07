package vet.goat.service.payload;

import org.springframework.stereotype.Service;
import vet.goat.entity.payload.Medication;
import vet.goat.exceptions.NoSuchMedicationException;

import java.util.List;

@Service
public interface MedicationService{
    List<Medication> getMedicationByName(String name);

    Medication getMedicationByFullParams(String name, String injectionType) throws NoSuchMedicationException;

    void updateMedication(Medication medication);

    void create(Medication medication);
}
