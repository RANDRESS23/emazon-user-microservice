package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.PermissionEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.exception.AlreadyExistsException;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Role role = new Role("Aux Bodega", Set.of(new Permission(1L, PermissionEnum.READ)), RoleEnum.AUX_BODEGA, 1L);
        RoleEntity roleEntity = new RoleEntity(1L, RoleEnum.AUX_BODEGA, "Aux Bodega", Set.of(new PermissionEntity(1L, PermissionEnum.READ)));
        user = new User.UserBuilder()
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

        userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setName("John");
        userEntity.setLastName("Doe");
        userEntity.setDocument("123456789");
        userEntity.setPhone("1234567890");
        userEntity.setBirthdate(LocalDate.of(1990, 1, 1));
        userEntity.setEmail("johndoe@example.com");
        userEntity.setRole(roleEntity);
        userEntity.setPassword("password123");
    }

    @Test
    void saveUser_ShouldThrowException_WhenDocumentExists() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.of(userEntity));

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
    void saveUser_ShouldThrowException_WhenEmailExists() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
    void saveUser_ShouldThrowException_WhenPhoneExists() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(user.getPhone())).thenReturn(Optional.of(userEntity));

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
    void saveUser_ShouldSaveUser_WhenNoConflictsExist() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(user.getPhone())).thenReturn(Optional.empty());
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        // Act
        userAdapter.saveUser(user);

        // Assert
        verify(userRepository).save(userEntity);
    }

    @Test
    void getUserByDocument_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = userAdapter.getUserByDocument(user.getDocument());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getUserId(), result.get().getUserId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getLastName(), result.get().getLastName());
        assertEquals(user.getDocument(), result.get().getDocument());
        assertEquals(user.getEmail(), result.get().getEmail());
        assertEquals(user.getPhone(), result.get().getPhone());
        assertEquals(user.getBirthdate(), result.get().getBirthdate());
    }

    @Test
    void getUserByDocument_ShouldReturnEmptyOptional_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findByDocument(user.getDocument())).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userAdapter.getUserByDocument(user.getDocument());

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void getUserByEmail_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = userAdapter.getUserByEmail(userEntity.getEmail());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getUserId(), result.get().getUserId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getLastName(), result.get().getLastName());
        assertEquals(user.getDocument(), result.get().getDocument());
        assertEquals(user.getEmail(), result.get().getEmail());
        assertEquals(user.getPhone(), result.get().getPhone());
        assertEquals(user.getBirthdate(), result.get().getBirthdate());
    }

    @Test
    void getUserByEmail_ShouldReturnEmptyOptional_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userAdapter.getUserByEmail(user.getEmail());

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void getUserByPhone_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findByPhone(user.getPhone())).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = userAdapter.getUserByPhone(user.getPhone());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getUserId(), result.get().getUserId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getLastName(), result.get().getLastName());
        assertEquals(user.getDocument(), result.get().getDocument());
        assertEquals(user.getEmail(), result.get().getEmail());
        assertEquals(user.getPhone(), result.get().getPhone());
        assertEquals(user.getBirthdate(), result.get().getBirthdate());
    }

    @Test
    void getUserByPhone_ShouldReturnEmptyOptional_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findByPhone(user.getPhone())).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userAdapter.getUserByPhone(user.getPhone());

        // Assert
        assertFalse(result.isPresent());
    }
}