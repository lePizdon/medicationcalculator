package vet.goat.medicationcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.goat.medicationcalculator.dto.DosageRange;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.exception.NoSuchDosageException;
import vet.goat.medicationcalculator.repository.DosageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DosageServiceImpl implements DosageService {
    private final DosageRepository repo;

    @Override
    public List<Dosage> getDosageByFullParams(String medicationName, String animalType, String injectionType) throws NoSuchDosageException {
        if (animalType != null && injectionType != null) {
            List<Dosage> resultList = new ArrayList<>();
            resultList.add(repo.getByFullParams(medicationName, animalType, injectionType));
            return resultList;
        } else if (animalType != null) {
            return repo.getByPairAnimal(medicationName, animalType);
        } else if(injectionType != null) {
            return repo.getByPair(medicationName, injectionType);
        }
        return repo.getByName(medicationName);
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
    public DosageRange getDosageValue(String medicationName, String injectionType, String animalType) {
        return repo.getDosageValue(medicationName, injectionType, animalType);
    }

    @Override
    public Optional<Double> getActiveSubstanceByFullParams(String medicationName, String animalType, String injectionType) {
        return repo.getSubstance(medicationName, animalType, injectionType);
    }

    @Override
    public List<Dosage> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteDosageById(Long id) {
        repo.deleteById(id);
    }
}
