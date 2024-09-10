package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.api.IAuthServicePort;
import com.emazon.microservicio_usuario.domain.exception.InvalidCredentials;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IAuthPersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

public class AuthUseCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        if (!authPersistencePort.validateCredentials(email, password)) {
            throw new InvalidCredentials(DomainConstants.INVALID_CREDENTIALS_MESSAGE);
        }

        User user = authPersistencePort.authenticate(email, password);
        return authPersistencePort.generateToken(user);
    }
}
