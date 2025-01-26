package vet.goat.medicationcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vet.goat.medicationcalculator.dto.DosageRange;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.entity.Medication;

import java.util.List;
import java.util.Optional;

@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {
    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name " +
            "AND animal_type = :aType AND medication_injection_type = :iType",
            nativeQuery = true)
    Dosage getByFullParams(@Param("name")String medicationName, @Param("aType")String animalType,
                                     @Param("iType") String injectionType);

    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name", nativeQuery = true)
    List<Dosage> getByName(@Param("name")String name);

    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name" +
            " AND medication_injection_type = :injectionType", nativeQuery = true)
    List<Dosage> getByPair(@Param("name") String medicationName,
                           @Param("injectionType") String injectionType);

    @Query(value = "SELECT dosages.start_value, dosages.end_value " +
            "FROM dosages WHERE medication_name = :name " +
            "AND medication_injection_type = :injection " +
            "AND animal_type = :animalType", nativeQuery = true)
    DosageRange getDosageValue(@Param("name") String medicationName, @Param("injection")String injectionType,
                               @Param("animalType")String animalType);

    @Query(value = "SELECT medications.active_substance FROM medications " +
            "JOIN dosages ON medications.id = dosages.medication_id " +
            "WHERE medications.name = :name AND dosages.animal_type = :animalType " +
            "AND medications.injection_type = :injectionType", nativeQuery = true)
    Optional<Double> getSubstance(String medicationName, String animalType, String injectionType);

    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name " +
            "AND animal_type = :aType", nativeQuery = true)
    List<Dosage> getByPairAnimal(@Param("name") String medicationName,@Param("aType") String animalType);
}
