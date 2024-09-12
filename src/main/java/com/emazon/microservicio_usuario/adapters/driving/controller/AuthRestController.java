package com.emazon.microservicio_usuario.adapters.driving.controller;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AuthenticationRequest;
import com.emazon.microservicio_usuario.adapters.driving.dto.response.AuthenticationResponse;
import com.emazon.microservicio_usuario.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_usuario.domain.api.IAuthServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_AUTH_NAME, description = DrivingConstants.TAG_AUTH_DESCRIPTION)
public class AuthRestController {
    private final IAuthServicePort authServicePort;

    @Operation(summary = DrivingConstants.SWAGGER_LOGIN_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_200, description = DrivingConstants.SWAGGER_LOGIN_RESPONSE),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SWAGGER_LOGIN_ERROR),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SWAGGER_VALIDATIONS_DONT_PASS),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        String token = authServicePort.login(request.getEmail(), request.getPassword());
        AuthenticationResponse response = new AuthenticationResponse(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
