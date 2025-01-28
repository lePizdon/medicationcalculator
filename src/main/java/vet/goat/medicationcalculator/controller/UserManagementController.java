package vet.goat.medicationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vet.goat.medicationcalculator.security.VetUserDetailService;

@RestController
@RequestMapping("/api/management")
public class UserManagementController {
    private final VetUserDetailService vetUserDetailService;

    @Autowired
    public UserManagementController(VetUserDetailService vetUserDetailService) {
        this.vetUserDetailService = vetUserDetailService;
    }
}
