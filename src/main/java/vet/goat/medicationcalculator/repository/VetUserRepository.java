package vet.goat.medicationcalculator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vet.goat.medicationcalculator.entity.VetUser;

import java.util.Optional;

@Repository
public interface VetUserRepository extends CrudRepository<VetUser, Long> {

    Optional<VetUser> findByUserName(String userName);
}
