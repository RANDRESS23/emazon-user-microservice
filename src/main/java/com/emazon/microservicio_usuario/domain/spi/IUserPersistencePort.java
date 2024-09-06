package com.emazon.microservicio_usuario.domain.spi;

import com.emazon.microservicio_usuario.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);
    Optional<User> getUserByDocument(String name);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByPhone(String phone);
}
