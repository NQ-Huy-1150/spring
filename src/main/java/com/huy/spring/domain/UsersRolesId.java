package com.huy.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersRolesId implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "role_name")
    String roleName;
}
