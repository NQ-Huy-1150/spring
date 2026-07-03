package com.huy.spring.domain.dto.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotNull
    String id;
    @NotEmpty(message = "INVALID_FIRST_NAME")
    String firstName;
    @NotEmpty(message = "INVALID_LAST_NAME")
    String lastName;
    LocalDate dob;
}
