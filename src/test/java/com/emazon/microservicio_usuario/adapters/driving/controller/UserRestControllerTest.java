package com.emazon.microservicio_usuario.adapters.driving.controller;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddUserRequest;
import com.emazon.microservicio_usuario.adapters.driving.dto.response.UserResponse;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserRequestMapper;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IUserResponseMapper;
import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.model.Permission;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRestControllerTest {
    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @Mock
    private IRoleServicePort roleServicePort;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveWarehouseAsstUser_shouldReturnCreatedUserResponse() {
        // Arrange
        Role role = new Role("Aux Bodega", Set.of(new Permission(1L, PermissionEnum.READ)), RoleEnum.AUX_BODEGA, 1L);
        AddUserRequest addUserRequest = new AddUserRequest(
                "John",
                "Doe",
                "123456789",
                "1234567890",
                "01/01/1990",
                "johndoe@example.com",
                "password123"
        );
        User user = new User.UserBuilder()
                .userId(1L)
                .name("John")
                .lastName("Doe")
                .document("123456789")
                .phone("1234567890")
                .birthdate(LocalDate.of(1990, 1, 1))
                .email("johndoe@example.com")
                .password("password123")
                .role(role)
                .build();
        UserResponse expectedResponse = new UserResponse(
                1L,
                "John",
                "Doe",
                "123456789",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe@example.com",
                role
        );

        when(roleServicePort.getRole(RoleEnum.AUX_BODEGA)).thenReturn(role);
        when(userResponseMapper.toUserResponse(user)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<UserResponse> response = userRestController.addAuxBodegaUser(addUserRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void saveClient_shouldReturnCreatedUserResponse() {
        // Arrange
        Role role = new Role("Client", Set.of(new Permission(1L, PermissionEnum.READ)), RoleEnum.CLIENTE, 1L);
        AddUserRequest addUserRequest = new AddUserRequest(
                "John",
                "Doe",
                "123456789",
                "1234567890",
                "01/01/1990",
                "johndoe@example.com",
                "password123"
        );
        User user = new User.UserBuilder()
                .userId(1L)
                .name("John")
                .lastName("Doe")
                .document("123456789")
                .phone("1234567890")
                .birthdate(LocalDate.of(1990, 1, 1))
                .email("johndoe@example.com")
                .password("password123")
                .role(role)
                .build();
        UserResponse expectedResponse = new UserResponse(
                1L,
                "John",
                "Doe",
                "123456789",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe@example.com",
                role
        );

        when(roleServicePort.getRole(RoleEnum.AUX_BODEGA)).thenReturn(role);
        when(userResponseMapper.toUserResponse(user)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<UserResponse> response = userRestController.addAuxBodegaUser(addUserRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}