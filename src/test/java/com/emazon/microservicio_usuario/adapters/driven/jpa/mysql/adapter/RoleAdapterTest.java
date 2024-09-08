package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.model.Permission;
import com.emazon.microservicio_usuario.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleAdapterTest {
    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleAdapter roleAdapter;

    private RoleEntity roleEntity;
    private Role role;
    private final RoleEnum roleEnum = RoleEnum.ADMIN;
    Set<Permission> permissions = Set.of(new Permission(1L, PermissionEnum.READ));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roleEntity = new RoleEntity();
        roleEntity.setRoleId(1L);
        roleEntity.setName(roleEnum);

        role = new Role("Admin role", permissions, roleEnum, 1L);
    }

    @Test
    void getRoleByName_ReturnsRole_WhenRoleExists() {
        // Arrange
        when(roleRepository.findByName(roleEnum)).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toDomainModel(roleEntity)).thenReturn(role);

        // Act
        Optional<Role> result = roleAdapter.getRoleByName(roleEnum);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(role, result.get());
    }

    @Test
    void getRoleByName_ReturnsEmptyOptional_WhenRoleDoesNotExist() {
        // Arrange
        when(roleRepository.findByName(roleEnum)).thenReturn(Optional.empty());

        // Act
        Optional<Role> result = roleAdapter.getRoleByName(roleEnum);

        // Assert
        assertTrue(result.isEmpty());
    }
}