package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.model.UserAccount;
import fr.noelh.sportsnoteapi.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findUserAccountByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User loading = ERROR : UserAccount email : '"+email+"' not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userAccount.getRoles()
                .forEach(role -> authorities.add(
                        new SimpleGrantedAuthority(role.getRoleEnum().toString())));
        return new User(userAccount.getEmail(), userAccount.getPassword(), authorities);
    }
}
