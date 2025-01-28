package vet.goat.medicationcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vet.goat.medicationcalculator.entity.VetUser;

import java.util.Optional;

@Repository
public interface VetUserRepository extends JpaRepository<VetUser, Long> {

    Optional<VetUser> findVetUserByUserName(String userName);
}
