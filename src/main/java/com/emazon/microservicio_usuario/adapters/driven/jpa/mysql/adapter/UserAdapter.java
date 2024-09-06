package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.exception.AlreadyExistsException;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if (userRepository.findByDocument(user.getDocument()).isPresent()) {
            throw new AlreadyExistsException(DrivenConstants.DOCUMENT_ALREADY_EXISTS_MESSAGE);
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsException(DrivenConstants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }

        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            throw new AlreadyExistsException(DrivenConstants.PHONE_ALREADY_EXISTS_MESSAGE);
        }

        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Optional<User> getUserByDocument(String name) {
        return userRepository.findByDocument(name)
                .map(userEntityMapper::toDomainModel);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toDomainModel);
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(userEntityMapper::toDomainModel);
    }
}
