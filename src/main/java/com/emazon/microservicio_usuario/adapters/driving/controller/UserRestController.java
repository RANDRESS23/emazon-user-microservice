package com.emazon.microservicio_usuario.adapters.driving.controller;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddUserRequest;
import com.emazon.microservicio_usuario.adapters.driving.dto.response.UserResponse;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserRequestMapper;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserResponseMapper;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IRoleServicePort roleServicePort;

    @PostMapping("/aux-bodega-user")
    public ResponseEntity<UserResponse> addAuxBodegaUser(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        Role role = roleServicePort.getRole(DrivingConstants.AUX_BODEGA_USER_ROLE);

        user.setRole(role);
        userServicePort.saveAuxBodegaUser(user);

        UserResponse response = userResponseMapper.toUserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
