package vet.goat.medicationcalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dosages",
       uniqueConstraints = {@UniqueConstraint(columnNames =
               {"animal_type", "medication_name", "medication_injection_type"})},
       schema = "public")
@Builder
public class Dosage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_value", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    private Double startValue;

    @Column(name = "end_value")
    private Double endValue;

    @Column(name = "animal_type", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    @Size(min = 3, max =  15, message = "{entity.validation.size}")
    private String animalType;

    @Column(name= "medication_id", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    private Long medicationId;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "medication_injection_type")
    @Enumerated(EnumType.STRING)
    private Medication.InjectionType medicationInjectionType;
}


