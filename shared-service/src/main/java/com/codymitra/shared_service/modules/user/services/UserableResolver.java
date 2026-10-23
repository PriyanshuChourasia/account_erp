package com.codymitra.shared_service.modules.user.services;


import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.user.enums.UserableTypeEnum;
import com.codymitra.shared_service.modules.user.interfaces.Userable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserableResolver {

    private final EmployeeRepository employeeRepository;

    public Userable resolve(UserableTypeEnum type, UUID id){
        return switch (type){
            case EMPLOYEE -> employeeRepository.findById(id).orElseThrow(
                    () -> new DataNotFoundException("Employee does not exists with this id")
            );
        };
    }
}
