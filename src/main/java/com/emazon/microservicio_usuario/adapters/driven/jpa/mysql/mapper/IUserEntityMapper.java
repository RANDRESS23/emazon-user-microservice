package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    User toDomainModel(UserEntity userEntity);
}
