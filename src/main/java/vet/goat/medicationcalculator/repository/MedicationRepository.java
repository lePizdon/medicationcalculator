package vet.goat.medicationcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vet.goat.medicationcalculator.entity.Medication;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query(value = "SELECT * FROM medications WHERE name = :name", nativeQuery = true)
    List<Medication> getByName(@Param("name") String name);

    @Query(value = "SELECT * FROM medications WHERE name = :name AND injection_type = :type", nativeQuery = true)
    Optional<Medication> getByFullParams(@Param("name") String name, @Param("type") String injectionType);
}
