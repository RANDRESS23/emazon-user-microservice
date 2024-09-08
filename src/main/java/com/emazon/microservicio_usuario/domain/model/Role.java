package com.emazon.microservicio_usuario.domain.model;

import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class Role {
    private final Long roleId;
    private final RoleEnum name;
    private final String description;
    private Set<Permission> permissionList;

    public Role(String description, Set<Permission> permissionList, RoleEnum name, Long roleId) {
        this.roleId = roleId;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.permissionList = permissionList;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleEnum getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
