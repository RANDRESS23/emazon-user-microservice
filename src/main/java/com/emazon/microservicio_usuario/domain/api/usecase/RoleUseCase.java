package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.exception.NotFoundException;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.spi.IRolePersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRole(RoleEnum name) {
        return rolePersistencePort.getRoleByName(name)
                .orElseThrow(() -> new NotFoundException(DomainConstants.ROLE_NOT_FOUND));
    }
}
