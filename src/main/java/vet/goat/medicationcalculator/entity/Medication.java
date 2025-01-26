package vet.goat.medicationcalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medications",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"name", "injection_type"})},
        schema = "public")
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    @Size(min = 3, max = 20, message = "{entity.validation.size}")
    private String name;

    @Column(name = "injection_type", nullable = false)
    @NotNull(message = "{entity.validation.notnull}")
    @Enumerated(EnumType.STRING)
    private InjectionType injectionType;

    @Column(name = "active_substance")
    @NotNull(message = "{entity.validation.notnull}")
    private Double activeSubstance;

    @Getter
    public enum InjectionType {
        NEEDLE_VEIN("внутривенно"), NEEDLE_SKIN("подкожно"),
        NEEDLE_MUSCLE("внутримышечно"), PILL("таблетка"),
        CAPSULE("капсула"), SUSPENSION("суспензия");
        private final String rep;
        InjectionType(String rep) {
            this.rep = rep;
        }
    }
}
