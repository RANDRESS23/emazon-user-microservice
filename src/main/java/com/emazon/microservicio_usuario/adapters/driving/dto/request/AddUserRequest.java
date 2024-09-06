package com.emazon.microservicio_usuario.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddUserRequest {
    private final String name;
    private final String lastName;
    private final String document;
    private final String phone;
    private final String birthdate;
    private final String email;
    private String password;
}
