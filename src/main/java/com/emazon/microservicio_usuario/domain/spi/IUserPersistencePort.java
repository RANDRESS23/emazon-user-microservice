package com.emazon.microservicio_usuario.domain.spi;

import com.emazon.microservicio_usuario.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveAuxBodegaUser(User user);
    Optional<User> getAuxBodegaUserByDocument(String name);
    Optional<User> getAuxBodegaUserByEmail(String email);
    Optional<User> getAuxBodegaUserByPhone(String phone);
}
