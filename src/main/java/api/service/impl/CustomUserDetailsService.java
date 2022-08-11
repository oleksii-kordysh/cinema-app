package api.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import api.model.User;
import api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return withUsername(username)
                .password(userFromDb.getPassword())
                .roles(userFromDb.getRoles().stream()
                .map(role -> role.getRoleName().name()).toArray(String[]::new))
                .build();
    }
}
