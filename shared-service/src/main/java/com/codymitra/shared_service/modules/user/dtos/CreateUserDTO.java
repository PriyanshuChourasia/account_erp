package com.codymitra.shared_service.modules.user.dtos;

import com.codymitra.shared_service.modules.user.enums.UserableTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateUserDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotNull(message = "Contact number is required")
        Long contactNo,

        @NotNull(message = "Userable type is required")
        UserableTypeEnum userableType,

        @NotNull(message = "Userable id is required")
        UUID userableId,

        Long altContactNo,



        String telePhoneNo,

        String faxNo,

        String website,

        @NotBlank(message = "Password is required")
        String password,

        LocalDate dateOfBirth,

        // This is not to be considered as in this software this is yet to be planned
        String licenseNo
) {
}
