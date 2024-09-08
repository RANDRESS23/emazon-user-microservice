package com.emazon.microservicio_usuario.domain.spi;

import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getRoleByName(RoleEnum name);
}
