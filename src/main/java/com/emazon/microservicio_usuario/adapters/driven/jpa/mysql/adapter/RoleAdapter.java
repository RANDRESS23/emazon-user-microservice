package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.exception.AlreadyExistsException;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new AlreadyExistsException(DrivenConstants.ROLE_ALREADY_EXISTS_MESSAGE);
        }

        roleRepository.save(roleEntityMapper.toEntity(role));
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(roleEntityMapper::toDomainModel);
    }
}
