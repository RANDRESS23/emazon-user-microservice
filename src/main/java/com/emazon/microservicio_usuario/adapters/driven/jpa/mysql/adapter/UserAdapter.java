package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper);
    }

    @Override
    public Optional<User> getUserByDocument(String name) {
        return userRepository.findByDocument(name)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(userEntity -> IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper));
    }
}
