package com.emazon.microservicio_usuario.domain.spi;

import com.emazon.microservicio_usuario.domain.model.User;

public interface IAuthPersistencePort {
    boolean validateCredentials(String email, String password);
    User authenticate(String email, String password);
    String generateToken(User user);
}
