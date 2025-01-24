package vet.goat.medicationcalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dosages",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"animal_type", "medication_name",
               "medication_injection_type"})}, schema = "public")
public class Dosage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    private Double value;

    @Column(name = "animal_type", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    @Size(min = 3, max = 15, message = "{entity.validation.size}")
    private String animalType;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "medication_injection_type")
    private Medication.InjectionType medicationInjectionType;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
}


