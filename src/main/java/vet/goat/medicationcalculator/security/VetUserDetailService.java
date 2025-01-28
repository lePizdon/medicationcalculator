package vet.goat.medicationcalculator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vet.goat.medicationcalculator.entity.Authority;
import vet.goat.medicationcalculator.entity.VetUser;
import vet.goat.medicationcalculator.repository.AuthorityRepository;
import vet.goat.medicationcalculator.repository.VetUserRepository;
import vet.goat.medicationcalculator.security.exception.VetUserAlreadyExists;
import vet.goat.medicationcalculator.security.exception.VetUserDoesNotExist;

import java.util.Collections;
import java.util.List;

@Service
public class VetUserDetailService implements UserDetailsService {

    private final VetUserRepository vetUserRepository;

    private final AuthorityRepository authorityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public VetUserDetailService(VetUserRepository vetUserRepository,
                                AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
        this.vetUserRepository = vetUserRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.vetUserRepository.findVetUserByUserName(username)
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

    public void createUser(VetUser vetUser) throws VetUserAlreadyExists {
        VetUser comparable = vetUserRepository.findVetUserByUserName(vetUser.getUserName()).orElse(null);
        if (comparable != null) {
            throw new VetUserAlreadyExists("{security.user.already.exists}");
        }
        vetUser.setAuthorities(authorityRepository.findByRole("ROLE_USER"));
        vetUser.setUserPasswordHash("{bcrypt}%s".formatted
                (bCryptPasswordEncoder.encode(vetUser.getUserPasswordHash())));
        vetUserRepository.save(vetUser);
    }

    public VetUser findVetUserById(Long id) throws VetUserDoesNotExist {
        return vetUserRepository.findById(id)
                .orElseThrow(() -> new VetUserDoesNotExist("{security.user.not.exists}"));
    }

    public List<VetUser> findAll() {
        return vetUserRepository.findAll();
    }

    public void deleteById(Long userId) throws VetUserDoesNotExist {
        VetUser comparable = vetUserRepository.findById(userId).orElse(null);
        if (comparable != null) {
            vetUserRepository.delete(comparable);
        } else {
            throw new VetUserDoesNotExist("{security.user.not.exists}");
        }
    }

    public void editUser(VetUser vetUser) throws VetUserDoesNotExist, VetUserAlreadyExists {
        if (vetUserRepository.existsById(vetUser.getId())) {
            VetUser comparable = vetUserRepository.findById(vetUser.getId()).orElse(null);
            if (comparable != null && comparable.equals(vetUser)) {
                throw new VetUserAlreadyExists("{security.user.already.exists}");
            } else {
                vetUser.setUserPasswordHash("{bcrypt}%s"
                        .formatted(bCryptPasswordEncoder
                                .encode(vetUser.getUserPasswordHash())));
                vetUserRepository.save(vetUser);
            }
        } else {
            throw new VetUserDoesNotExist("{security.user.not.exists}");
        }
    }
}
