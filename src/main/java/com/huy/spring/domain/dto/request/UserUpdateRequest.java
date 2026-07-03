package com.huy.spring.domain.dto.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotNull
    private String id;
    @NotEmpty(message = "FirstName can not empty")
    private String firstName;
    @NotEmpty(message = "LastName can not empty")
    private String lastName;
    private LocalDate dob;
}
