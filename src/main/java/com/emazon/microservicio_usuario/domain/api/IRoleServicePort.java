package com.emazon.microservicio_usuario.domain.api;

import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.model.Role;

public interface IRoleServicePort {
    Role getRole(RoleEnum name);
}
