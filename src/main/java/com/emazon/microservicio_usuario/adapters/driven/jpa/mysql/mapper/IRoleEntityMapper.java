package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    Role toDomainModel(RoleEntity roleEntity);
}
