package vet.goat.medicationcalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "security")
public class VetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @Size(min = 4, max = 15)
    @NotNull(message = "{entity.validation.notnull}")
    private String userName;

    @Column(name = "user_password_hash")
    @Size(min = 4, max = 13)
    @NotNull(message = "{entity.validation.notnull}")
    private String userPasswordHash;

    @ManyToMany
    @JoinTable(schema = "security", name = "users_authority",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_authority"))
    private List<Authority> authorities;
}
