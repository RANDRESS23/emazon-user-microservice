package com.emazon.microservicio_usuario.adapters.driving.mapper;

import com.emazon.microservicio_usuario.adapters.driving.dto.response.UserResponse;
import com.emazon.microservicio_usuario.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponse toUserResponse(User user);
}
