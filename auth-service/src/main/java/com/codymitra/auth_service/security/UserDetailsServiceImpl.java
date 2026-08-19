package com.codymitra.auth_service.security;


import com.codymitra.shared_service.modules.user.entities.UserEntity;
import com.codymitra.shared_service.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
        return new UserPrincipal(user);
    }
}
