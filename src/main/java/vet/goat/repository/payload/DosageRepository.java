package vet.goat.repository.payload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vet.goat.dto.DosageDetailsDTO;
import vet.goat.dto.DosageRange;
import vet.goat.entity.payload.Dosage;

import java.util.List;
import java.util.Optional;

@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {
    @Query(value = "SELECT dosages.start_value, end_value FROM dosages WHERE medication_name = :name " +
            "AND animal_type = :aType AND medication_injection_type = :iType",
            nativeQuery = true)
    Optional<Dosage> getByFullParams(@Param("name")String medicationName, @Param("aType")String animalType,
                                     @Param("iType") String injectionType);

    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name", nativeQuery = true)
    List<Dosage> getByName(@Param("name")String name);

    @Query(value = "SELECT * FROM dosages WHERE medication_name = :name " +
            "AND medication_injection_type = :injectionType", nativeQuery = true)
    List<Dosage> getByPair(@Param("name") String medicationName,
                           @Param("injectionType") String injectionType);

    @Query(value = "SELECT dosages.start_value, dosages.end_value " +
            "FROM dosages WHERE medication_name = :name " +
            "AND medication_injection_type = :injection " +
            "AND animal_type = :animalType", nativeQuery = true)
    DosageRange getDosageValue(@Param("name") String medicationName, @Param("injection")String injectionType,
                               @Param("animalType")String animalType);
}
