package com.emazon.microservicio_usuario.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AuthenticationRequest {
    private final String email;
    private final String password;
}
