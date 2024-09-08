package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.exception.AlreadyExistsFieldException;
import com.emazon.microservicio_usuario.domain.model.Permission;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IEncryptionPersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;
import com.emazon.microservicio_usuario.domain.validation.UserValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncryptionPersistencePort encryptionPersistencePort;

    @Mock
    private UserValidation userValidation;

    @InjectMocks
    private UserUseCase userUseCase;

    private User createUser() {
        Role role = new Role("Aux Bodega", Set.of(new Permission(1L, PermissionEnum.READ)), RoleEnum.AUX_BODEGA, 1L);

        return new User.UserBuilder()
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
    }

    @Test
    void testSaveAuxBodegaUserSuccessfully() {
        // Arrange
        User user = createUser();

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByPhone(user.getPhone())).thenReturn(Optional.empty());
        when(encryptionPersistencePort.encodePassword(user.getPassword())).thenReturn("encryptedPassword");

        // Act
        userUseCase.saveAuxBodegaUser(user);

        // Assert
        verify(userValidation).validateUser(user);
        assertEquals("encryptedPassword", user.getPassword());
        verify(userPersistencePort).saveUser(user);
    }

    @Test
    void testSaveAuxBodegaUserDocumentAlreadyExists() {
        // Arrange
        User user = createUser();

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(Optional.of(user));

        // Act & Assert
        AlreadyExistsFieldException exception = assertThrows(
                AlreadyExistsFieldException.class,
                () -> userUseCase.saveAuxBodegaUser(user)
        );

        assertEquals(DomainConstants.DOCUMENT_ALREADY_EXISTS_MESSAGE, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void testSaveAuxBodegaUserEmailAlreadyExists() {
        // Arrange
        User user = createUser();

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Act & Assert
        AlreadyExistsFieldException exception = assertThrows(
                AlreadyExistsFieldException.class,
                () -> userUseCase.saveAuxBodegaUser(user)
        );

        assertEquals(DomainConstants.EMAIL_ALREADY_EXISTS_MESSAGE, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void testSaveAuxBodegaUserPhoneAlreadyExists() {
        // Arrange
        User user = createUser();

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByPhone(user.getPhone())).thenReturn(Optional.of(user));

        // Act & Assert
        AlreadyExistsFieldException exception = assertThrows(
                AlreadyExistsFieldException.class,
                () -> userUseCase.saveAuxBodegaUser(user)
        );

        assertEquals(DomainConstants.PHONE_ALREADY_EXISTS_MESSAGE, exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void testSaveAuxBodegaUserValidationFails() {
        // Arrange
        User user = createUser();

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByPhone(user.getPhone())).thenReturn(Optional.empty());

        doThrow(new RuntimeException("Validation failed")).when(userValidation).validateUser(user);

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userUseCase.saveAuxBodegaUser(user)
        );

        assertEquals("Validation failed", exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }
}