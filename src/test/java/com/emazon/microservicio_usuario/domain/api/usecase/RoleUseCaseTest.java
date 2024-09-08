package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.exception.NotFoundException;
import com.emazon.microservicio_usuario.domain.model.Permission;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.spi.IRolePersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void testGetRoleSuccessfully() {
        // Arrange
        RoleEnum roleName = RoleEnum.ADMIN;
        Set<Permission> permissions = Set.of(new Permission(1L, PermissionEnum.READ));
        Role role = new Role("Admin role", permissions, roleName, 1L);

        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(Optional.of(role));

        // Act
        Role result = roleUseCase.getRole(roleName);

        // Assert
        assertNotNull(result);
        assertEquals(roleName, result.getName());
        assertEquals("Admin role", result.getDescription());
        assertEquals(1, result.getPermissionList().size());
        assertEquals(PermissionEnum.READ, result.getPermissionList().iterator().next().getName());
    }

    @Test
    void testGetRoleNotFound() {
        // Arrange
        RoleEnum roleName = RoleEnum.ADMIN;

        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> roleUseCase.getRole(roleName)
        );

        assertEquals(DomainConstants.ROLE_NOT_FOUND, exception.getMessage());
    }
}