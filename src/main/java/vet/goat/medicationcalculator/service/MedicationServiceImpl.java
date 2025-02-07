package vet.goat.medicationcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.entity.Medication;
import vet.goat.medicationcalculator.exception.NoSuchDosageException;
import vet.goat.medicationcalculator.exception.NoSuchMedicationException;
import vet.goat.medicationcalculator.repository.MedicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repo;

    @Override
    public List<Medication> getMedicationByName(String name) {
        return repo.getByName(name);
    }

    @Override
    public void updateMedication(Medication medication) {
        repo.save(medication);
    }

    @Override
    public void create(Medication medication) {
        repo.save(medication);
    }

    @Override
    public Medication getMedicationById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Medication> findAll() {
        return repo.findAll();
    }


    @Override
    public List<Medication> getMedicationByParams(String medicationName, String injectionType)
            throws NoSuchMedicationException {
        if (medicationName != null && injectionType != null) {

        return List.of(repo.getByFullParams(medicationName, injectionType).orElseThrow(() ->
                new NoSuchMedicationException("{medication.get.full.params.null}")));
        } else if (injectionType == null && medicationName != null) {
            return repo.getByName(medicationName);
        } else if (injectionType != null) {
            return repo.getByType(injectionType);
        } else {
            return repo.findAll();
        }
    }
}
