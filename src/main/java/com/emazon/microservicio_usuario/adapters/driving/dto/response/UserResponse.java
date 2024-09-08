package com.emazon.microservicio_usuario.adapters.driving.dto.response;

import com.emazon.microservicio_usuario.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long userId;
    private final String name;
    private final String lastName;
    private final String document;
    private final String phone;
    private final LocalDate birthdate;
    private final String email;
    private Role role;
}
