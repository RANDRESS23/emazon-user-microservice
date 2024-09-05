package com.emazon.microservicio_usuario.domain.validation;

import com.emazon.microservicio_usuario.domain.exception.EmptyFieldException;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

public class RoleValidation {
    public void validateRole(Role role) {
        validateNameRole(role.getName());
        validateDescriptionRole(role.getDescription());
    }

    private void validateNameRole(String name) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
    }

    private void validateDescriptionRole(String description) {
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
    }
}
