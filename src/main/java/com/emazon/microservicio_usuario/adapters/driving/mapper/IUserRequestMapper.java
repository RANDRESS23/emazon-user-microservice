package com.emazon.microservicio_usuario.adapters.driving.mapper;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddUserRequest;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(target = DrivingConstants.USER_ID, ignore = true)
    @Mapping(source = DrivingConstants.USER_BIRTHDATE, target = DrivingConstants.USER_BIRTHDATE, qualifiedByName = DrivingConstants.FORMAT_FROM_STRING_TO_LOCAL_DATE)
    static User addRequestToUser(AddUserRequest addUserRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DrivingConstants.DATE_FORMAT);

        return new User.UserBuilder()
                .name(addUserRequest.getName())
                .lastName(addUserRequest.getLastName())
                .document(addUserRequest.getDocument())
                .phone(addUserRequest.getPhone())
                .birthdate(LocalDate.parse(addUserRequest.getBirthdate(), formatter))
                .email(addUserRequest.getEmail())
                .password(addUserRequest.getPassword())
        .build();
    }
}
