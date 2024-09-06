package com.emazon.microservicio_usuario.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddRoleRequest {
    private final String name;
    private final String description;
}
