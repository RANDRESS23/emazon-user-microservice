package com.emazon.microservicio_usuario.adapters.driving.mapper;

import com.emazon.microservicio_usuario.adapters.driving.dto.response.RoleResponse;
import com.emazon.microservicio_usuario.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleResponseMapper {
    RoleResponse toRoleResponse(Role role);
}
