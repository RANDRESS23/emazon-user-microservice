package com.emazon.microservicio_usuario.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleResponse {
    private final Long roleId;
    private final String name;
    private final String description;
}
