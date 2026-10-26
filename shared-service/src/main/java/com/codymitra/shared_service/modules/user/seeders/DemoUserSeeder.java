package com.codymitra.shared_service.modules.user.seeders;

import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.user.entities.UserEntity;
import com.codymitra.shared_service.modules.user.repositories.UserRepository;
import com.codymitra.shared_service.modules.user_type.enums.UserTypeCodeEnum;
import com.codymitra.shared_service.modules.user_type.services.UserTypeLookup;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Demo users for local development. Each user gets an employee record (userable) and a user type.
 * Not run on startup: use --seed=demo_user.
 */
@Component
@RequiredArgsConstructor
public class DemoUserSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/user/data/demo_users.json";

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final UserTypeLookup userTypeLookup;
    private final PasswordEncoder passwordEncoder;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "demo_user";
    }

    @Override
    public int order() {
        // after user_type (5)
        return 50;
    }

    @Override
    public boolean runOnStartup(){
        return false;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, DemoUser.class).stream()
                .filter(demoUser -> !userRepository.existsByEmail(demoUser.email()))
                .filter(demoUser -> !userRepository.existsByUsername(demoUser.username()))
                .filter(demoUser -> !userRepository.existsByContactNo(demoUser.contactNo()))
                .forEach(this::createUser);
    }

    private void createUser(DemoUser demoUser) {
        // reuse the employee if one already exists with this email
        EmployeeEntity employee = employeeRepository.findByEmail(demoUser.email())
                .orElseGet(() -> createEmployee(demoUser));

        UserEntity user = new UserEntity();
        user.setName(demoUser.name());
        user.setUsername(demoUser.username());
        user.setEmail(demoUser.email());
        user.setCountryCode(demoUser.countryCode());
        user.setContactNo(demoUser.contactNo());
        user.setCode(demoUser.code());
        user.setPassword(passwordEncoder.encode(demoUser.password()));
        user.setActive(true);
        user.setUserable(employee);
        user.setUserType(userTypeLookup.get(demoUser.userType()));
        userRepository.save(user);
    }

    private EmployeeEntity createEmployee(DemoUser demoUser) {
        String[] nameParts = demoUser.name().trim().split("\\s+", 2);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeCode("EMP_" + demoUser.code());
        employee.setFirstName(nameParts[0]);
        employee.setLastName(nameParts.length > 1 ? nameParts[1] : null);
        employee.setEmail(demoUser.email());
        employee.setContactNo(String.valueOf(demoUser.contactNo()));
        employee.setDateOfJoining(LocalDate.now());
        employee.setEmploymentType(EmploymentTypeEnum.FULL_TIME);
        employee.setStatus(EmployeeStatusEnum.ACTIVE);
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    private record DemoUser(
            String name,
            String username,
            String email,
            String password,
            String countryCode,
            Long contactNo,
            String code,
            UserTypeCodeEnum userType
    ) {
    }
}
