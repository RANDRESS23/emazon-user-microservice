package com.emazon.microservicio_usuario.adapters.driving.controller;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddUserRequest;
import com.emazon.microservicio_usuario.adapters.driving.dto.response.UserResponse;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserRequestMapper;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserResponseMapper;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_USER_NAME, description = DrivingConstants.TAG_USER_DESCRIPTION)
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserResponseMapper userResponseMapper;

    @Operation(summary = DrivingConstants.SAVE_AUX_BODEGA_USER_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_AUX_BODEGA_USER_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_AUX_BODEGA_USER_RESPONSE_400_DESCRIPTION)
    })
    @PreAuthorize(DrivingConstants.HAS_ROLE_ADMIN)
    @PostMapping("/aux-bodega-user")
    public ResponseEntity<UserResponse> addAuxBodegaUser(@Valid @RequestBody AddUserRequest request) {
        User user = IUserRequestMapper.addRequestToUser(request);
        User userSaved = userServicePort.saveAuxBodegaUser(user);

        UserResponse response = userResponseMapper.toUserResponse(userSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = DrivingConstants.SAVE_CLIENT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_CLIENT_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_CLIENT_RESPONSE_400_DESCRIPTION)
    })
    @PreAuthorize(DrivingConstants.HAS_ROLE_ADMIN)
    @PostMapping("/client")
    public ResponseEntity<UserResponse> addClient(@Valid @RequestBody AddUserRequest request) {
        User user = IUserRequestMapper.addRequestToUser(request);
        User userSaved = userServicePort.saveClient(user);

        UserResponse response = userResponseMapper.toUserResponse(userSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
