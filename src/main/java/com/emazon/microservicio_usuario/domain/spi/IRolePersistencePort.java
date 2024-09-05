package com.emazon.microservicio_usuario.domain.spi;

import com.emazon.microservicio_usuario.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    void saveRole(Role role);
    Optional<Role> getRoleByName(String name);
}
