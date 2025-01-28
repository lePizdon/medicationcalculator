package vet.goat.medicationcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vet.goat.medicationcalculator.entity.Authority;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Query(value = "SELECT * FROM security.authority WHERE authority_value = :param", nativeQuery = true)
    List<Authority> findByRole(@Param("param")String roleUser);
}
