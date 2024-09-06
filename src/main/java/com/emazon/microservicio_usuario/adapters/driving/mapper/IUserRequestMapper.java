package com.emazon.microservicio_usuario.adapters.driving.mapper;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddUserRequest;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(target = DrivingConstants.USER_ID, ignore = true)
    @Mapping(source = DrivingConstants.USER_BIRTHDATE, target = DrivingConstants.USER_BIRTHDATE, qualifiedByName = DrivingConstants.FORMAT_FROM_STRING_TO_LOCAL_DATE)
    User addRequestToUser(AddUserRequest addUserRequest);

    @Named(DrivingConstants.FORMAT_FROM_STRING_TO_LOCAL_DATE)
    default LocalDate formatFromStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DrivingConstants.DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }
}
