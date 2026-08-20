package com.codymitra.auth_service.services.impl;

import com.codymitra.auth_service.dtos.AuthRequestDTO;
import com.codymitra.auth_service.dtos.AuthResponseDTO;
import com.codymitra.auth_service.dtos.ProfileResponseDTO;
import com.codymitra.auth_service.dtos.UserRegisterDTO;
import com.codymitra.auth_service.mapper.AuthMapper;
import com.codymitra.auth_service.security.UserPrincipal;
import com.codymitra.auth_service.services.AuthService;
import com.codymitra.auth_service.services.JwtService;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.user.entities.UserEntity;
import com.codymitra.shared_service.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.email())) {
            throw new DataAlreadyExistsException("User already exists with this email");
        }
        if (userRepository.existsByContactNo(userRegisterDTO.contactNo())) {
            throw new DataAlreadyExistsException("User already exists with this contact number");
        }

        log.info("register service now");

        UserEntity user = new UserEntity();
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setContactNo(userRegisterDTO.contactNo());
        user.setCountryCode(userRegisterDTO.countryCode());
        user.setAltContactNo(userRegisterDTO.altContactNo());
        user.setDateOfBirth(userRegisterDTO.dateOfBirth());
        user.setActive(true);

        String username = generateUsername(userRegisterDTO.name());
        user.setUsername(username);

        String code = generateCode();
        user.setCode(code);

        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        log.info("set of suer");
        userRepository.save(user);

        log.info("username {}",user.getUsername());
        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        return new AuthResponseDTO(accessToken, refreshToken);
    }

    @Override
    public AuthResponseDTO authenticateUser(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.username(), authRequestDTO.password())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userPrincipal.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userPrincipal.getUsername());
        return new AuthResponseDTO(accessToken, refreshToken);
    }

    @Override
    public ProfileResponseDTO profile(String token) {
        String username = jwtService.extractUsername(token);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new DataNotFoundException("User not found")
        );

        return AuthMapper.profileResponseDTO(user);
    }

    private String generateUsername(String name) {
        String cleanName = name.replaceAll("\\s+", "").toLowerCase();
        String prefix = cleanName.substring(0, Math.min(4, cleanName.length()));
        String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        Random random = new Random();
        String username;
        do {
            String digits = String.format("%04d", random.nextInt(10000));
            username = prefix + yearMonth + digits + "@accounterp.com";
        } while (userRepository.existsByUsername(username));
        return username;
    }

    private String generateCode() {
        Random random = new Random();
        String code;
        do {
            code = String.format("%06d", random.nextInt(1000000));
        } while (userRepository.existsByCode(code));
        return code;
    }
}
