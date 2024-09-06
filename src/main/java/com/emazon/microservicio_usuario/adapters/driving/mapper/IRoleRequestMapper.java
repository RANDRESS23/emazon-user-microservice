package com.emazon.microservicio_usuario.adapters.driving.mapper;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddRoleRequest;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRoleRequestMapper {
    @Mapping(target = DrivingConstants.ROLE_ID, ignore = true)
    Role addRequestToRole(AddRoleRequest addRoleRequest);
}
