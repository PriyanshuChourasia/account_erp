package com.codymitra.auth_service.services.impl;

import com.codymitra.auth_service.dtos.AuthRequestDTO;
import com.codymitra.auth_service.dtos.AuthResponseDTO;
import com.codymitra.auth_service.dtos.ProfileResponseDTO;
import com.codymitra.auth_service.security.UserPrincipal;
import com.codymitra.auth_service.services.AuthService;
import com.codymitra.auth_service.services.JwtService;
import com.codymitra.shared_service.modules.user.entities.UserEntity;
import com.codymitra.shared_service.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public AuthResponseDTO authenticateUser(AuthRequestDTO authRequestDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.username(),authRequestDTO.password())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userPrincipal.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userPrincipal.getUsername());
        return new AuthResponseDTO(accessToken,refreshToken);
    }


    @Override
    public ProfileResponseDTO profile(String token){
        UserEntity user =
    }
}
