package com.huy.spring.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationRequest {
    @NotEmpty(message = "INVALID_FIRST_NAME")
    private String firstName;
    @NotEmpty(message = "INVALID_LAST_NAME")
    private String lastName;
    @Size(min = 6, message = "INVALID_USERNAME")
    private String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private LocalDate dob;
}
