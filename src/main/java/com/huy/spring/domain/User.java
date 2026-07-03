package com.huy.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String fullName;
    private LocalDate dob;
}
