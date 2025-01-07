package vet.goat.service.payload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.entity.payload.Medication;
import vet.goat.exceptions.NoSuchMedicationException;
import vet.goat.repository.payload.MedicationRepository;

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
    public Medication getMedicationByFullParams(String name, String injectionType) throws NoSuchMedicationException {
        return repo.getByFullParams(name, injectionType).orElseThrow(() ->
                new NoSuchMedicationException("Medication not found"));
    }

    @Override
    public void updateMedication(Medication medication) {
        repo.save(medication);
    }

    @Override
    public void create(Medication medication) {
        repo.save(medication);
    }
}
