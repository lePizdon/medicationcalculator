package vet.goat.medicationcalculator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vet.goat.medicationcalculator.entity.Authority;
import vet.goat.medicationcalculator.entity.VetUser;
import vet.goat.medicationcalculator.repository.VetUserRepository;

import java.util.List;

@Service
public class VetUserDetailService implements UserDetailsService {

    private final VetUserRepository vetUserRepository;

    @Autowired
    public VetUserDetailService(VetUserRepository vetUserRepository) {
        this.vetUserRepository = vetUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.vetUserRepository.findByUserName(username)
                .map(user -> User.builder()
                        .username(user.getUserName())
                        .password(user.getUserPasswordHash())
                        .authorities(user.getAuthorities().stream()
                                .map(Authority::getAuthorityValue)
                                .peek(role -> System.out.printf("Role: %s%n", role))
                                .map(SimpleGrantedAuthority::new)
                                .toList())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }

}
