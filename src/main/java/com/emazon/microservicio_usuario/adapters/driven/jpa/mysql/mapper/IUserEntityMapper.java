package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    public static User toDomainModel(UserEntity userEntity, IRoleEntityMapper roleEntityMapper) {
        Role role = roleEntityMapper.toDomainModel(userEntity.getRole());

        return new User.UserBuilder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .document(userEntity.getDocument())
                .phone(userEntity.getPhone())
                .birthdate(userEntity.getBirthdate())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(role)
                .build();
    }
}
