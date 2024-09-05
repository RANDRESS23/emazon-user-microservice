package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.exception.AlreadyExistsFieldException;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.spi.IRolePersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;
import com.emazon.microservicio_usuario.domain.validation.RoleValidation;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;
    private final RoleValidation roleValidation;

    public RoleUseCase(IRolePersistencePort rolePersistencePort, RoleValidation roleValidation) {
        this.rolePersistencePort = rolePersistencePort;
        this.roleValidation = roleValidation;
    }

    @Override
    public void saveRole(Role role) {
        if (rolePersistencePort.getRoleByName(role.getName()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.ROLE_ALREADY_EXISTS_MESSAGE);
        }

        roleValidation.validateRole(role);
        rolePersistencePort.saveRole(role);
    }

    @Override
    public Role getRole(String name) {
        return null;
    }
}
