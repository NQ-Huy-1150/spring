package com.huy.spring.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    LocalDate dob;
}
