package com.huy.spring.domain.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RolesPermissionsResponse {
    Set<String> permissions;
}
