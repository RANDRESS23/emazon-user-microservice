package com.emazon.microservicio_usuario.domain.model;

import com.emazon.microservicio_usuario.domain.exception.EmptyFieldException;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Role {
    private final Long roleId;
    private final String name;
    private final String description;

    public Role(Long roleId, String name, String description) {
        this.roleId = roleId;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
