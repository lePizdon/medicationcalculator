package vet.goat.service.payload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.dto.DosageRange;
import vet.goat.entity.payload.Dosage;
import vet.goat.exceptions.ActiveSubstanceNotPresented;
import vet.goat.exceptions.NoSuchDosageException;
import vet.goat.repository.payload.DosageRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DosageServiceImpl implements DosageService {
    private final DosageRepository repo;

    @Override
    public Dosage getDosageByFullParams(String medicationName, String animalType, String injectionType) throws NoSuchDosageException {
        return repo.getByFullParams(medicationName, animalType, injectionType).orElseThrow(() ->
                new NoSuchDosageException("{dosage.get.fullparams.null}"));
    }

    @Override
    public List<Dosage> getDosagesByName(String name) {
        return repo.getByName(name);
    }

    @Override
    public List<Dosage> getDosagesByPair(String medicationName, String injectionType) {
        return repo.getByPair(medicationName, injectionType);
    }

    @Override
    public void updateDosage(Dosage editable) {
        repo.save(editable);
    }

    @Override
    public void create(Dosage dosage) {
        repo.save(dosage);
    }

    @Override
    public Optional<Dosage> getDosageById(Long id)  {
        return repo.findById(id);
    }

    @Override
    public void deleteDosage(Dosage dosage) {
        repo.delete(dosage);
    }


    @Override
    public DosageRange getDosageValue(String medicationName, String animalType, String injectionType) {
        return repo.getDosageValue(medicationName, animalType, injectionType);
    }

    @Override
    public Optional<Double> getActiveSubstanceByFullParams(String medicationName, String animalType, String injectionType) {
        return repo.getSubstance(medicationName, animalType, injectionType);
    }
}
