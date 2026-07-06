package com.huy.spring.domain.dto.request;

import com.huy.spring.validator.DobConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotEmpty(message = "INVALID_FIRST_NAME")
    String firstName;
    @NotEmpty(message = "INVALID_LAST_NAME")
    String lastName;
    @Size(min = 5, message = "INVALID_USERNAME")
    @NotNull(message = "NULL_USERNAME")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    @NotNull(message = "NULL_PASSWORD")
    String password;
    @DobConstraint(min = 12, message = "DOB_NOT_VALID")
    LocalDate dob;

    Set<String> roles;
}
