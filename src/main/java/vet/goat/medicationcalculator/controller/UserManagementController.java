package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.goat.medicationcalculator.entity.VetUser;
import vet.goat.medicationcalculator.security.VetUserDetailService;
import vet.goat.medicationcalculator.security.exception.VetUserAlreadyExists;
import vet.goat.medicationcalculator.security.exception.VetUserDoesNotExist;

import java.util.List;

@RestController
@RequestMapping("/api/management")
public class UserManagementController {
    private final VetUserDetailService vetUserDetailService;

    @Autowired
    public UserManagementController(VetUserDetailService vetUserDetailService) {
        this.vetUserDetailService = vetUserDetailService;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<VetUser>> getAllUsers() {
        List<VetUser> resultList = vetUserDetailService.findAll();
        if (resultList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<VetUser> getUser(@PathVariable("userId") Long userId) {
        VetUser result;
        try {
            result = vetUserDetailService.findVetUserById(userId);
        } catch (VetUserDoesNotExist e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/users/create")
    public ResponseEntity<Void> createUser(@RequestBody VetUser vetUser) {
        try {
            vetUserDetailService.createUser(vetUser);
            return ResponseEntity.ok().build();
        } catch (VetUserAlreadyExists e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/users/edit")
    public ResponseEntity<Void> editUser(@RequestBody VetUser vetUser) {
        try {
            vetUserDetailService.editUser(vetUser);
        } catch (VetUserDoesNotExist | VetUserAlreadyExists e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {
        try {
            vetUserDetailService.deleteById(userId);
        } catch (VetUserDoesNotExist e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
