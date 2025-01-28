package vet.goat.medicationcalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority", schema = "security")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority_value")
    @NotNull(message = "{entity.validation.notnull}")
    private String authorityValue;

    public Authority(String authorityValue) {
        this.authorityValue = authorityValue;
    }
}
