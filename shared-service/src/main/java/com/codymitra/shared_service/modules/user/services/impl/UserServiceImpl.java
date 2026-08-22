package com.codymitra.shared_service.modules.user.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.user.dtos.CreateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UpdateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserRegisterDTO;
import com.codymitra.shared_service.modules.user.entities.UserEntity;
import com.codymitra.shared_service.modules.user.mappers.UserMapper;
import com.codymitra.shared_service.modules.user.repositories.UserRepository;
import com.codymitra.shared_service.modules.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream().map(UserMapper::userDTO).toList();
    }

    @Override
    public UserDTO getById(UUID id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User does not exist with this id")
        );
        return UserMapper.userDTO(entity);
    }

    @Override
    public String create(CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.email())) {
            throw new DataAlreadyExistsException("User already exists with this email");
        }
        if (userRepository.existsByContactNo(createUserDTO.contactNo())) {
            throw new DataAlreadyExistsException("User already exists with this contact number");
        }
        if (createUserDTO.licenseNo() != null && userRepository.existsByLicenseNo(createUserDTO.licenseNo())) {
            throw new DataAlreadyExistsException("User already exists with this license number");
        }

        UserEntity entity = UserMapper.userEntity(createUserDTO);

        String username = generateUsername(createUserDTO.name());
        entity.setUsername(username);

        String code = generateCode();
        entity.setCode(code);

        entity.setPassword(passwordEncoder.encode(createUserDTO.password()));

        userRepository.save(entity);
        return "User created successfully";
    }

    @Override
    public String update(UUID id, UpdateUserDTO updateUserDTO) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User does not exist with this id")
        );

        if (updateUserDTO.email() != null && !updateUserDTO.email().equals(entity.getEmail())) {
            if (userRepository.existsByEmail(updateUserDTO.email())) {
                throw new DataAlreadyExistsException("User already exists with this email");
            }
        }

        if (updateUserDTO.contactNo() != null && !updateUserDTO.contactNo().equals(entity.getContactNo())) {
            if (userRepository.existsByContactNo(updateUserDTO.contactNo())) {
                throw new DataAlreadyExistsException("User already exists with this contact number");
            }
        }

        if (updateUserDTO.licenseNo() != null && !updateUserDTO.licenseNo().equals(entity.getLicenseNo())) {
            if (userRepository.existsByLicenseNo(updateUserDTO.licenseNo())) {
                throw new DataAlreadyExistsException("User already exists with this license number");
            }
        }

        UserMapper.updateEntity(updateUserDTO, entity);

        if (updateUserDTO.password() != null) {
            entity.setPassword(passwordEncoder.encode(updateUserDTO.password()));
        }

        userRepository.save(entity);
        return "User updated successfully";
    }

    @Override
    public String delete(UUID id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("User does not exist with this id")
        );
        userRepository.delete(entity);
        return "User deleted successfully";
    }

    @Override
    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.email())) {
            throw new DataAlreadyExistsException("User already exists with this email");
        }
        if (userRepository.existsByContactNo(userRegisterDTO.contactNo())) {
            throw new DataAlreadyExistsException("User already exists with this contact number");
        }

        UserEntity entity = new UserEntity();
        entity.setName(userRegisterDTO.name());
        entity.setEmail(userRegisterDTO.email());
        entity.setContactNo(userRegisterDTO.contactNo());
        entity.setAltContactNo(userRegisterDTO.altContactNo());
        entity.setDateOfBirth(userRegisterDTO.dateOfBirth());
        entity.setActive(true);

        String username = generateUsername(userRegisterDTO.name());
        entity.setUsername(username);

        String code = generateCode();
        entity.setCode(code);

        entity.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

        userRepository.save(entity);
        return UserMapper.userDTO(entity);
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
