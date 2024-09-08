package com.emazon.microservicio_usuario.domain.model;

import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;

public class Permission {
    private final Long permissionId;
    private final PermissionEnum name;

    public Permission(Long permissionId, PermissionEnum name) {
        this.permissionId = permissionId;
        this.name = name;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public PermissionEnum getName() {
        return name;
    }
}
