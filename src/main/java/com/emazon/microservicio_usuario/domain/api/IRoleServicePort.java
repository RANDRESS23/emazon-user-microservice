package com.emazon.microservicio_usuario.domain.api;

import com.emazon.microservicio_usuario.domain.model.Role;

public interface IRoleServicePort {
    void saveRole(Role role);
    Role getRole(String name);
}
