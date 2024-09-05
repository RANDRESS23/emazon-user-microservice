package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.exception.AlreadyExistsFieldException;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IEncryptionPersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;
import com.emazon.microservicio_usuario.domain.validation.UserValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IEncryptionPersistencePort encryptionPersistencePort;
    private final UserValidation userValidation;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncryptionPersistencePort encryptionPersistencePort, UserValidation userValidation) {
        this.userPersistencePort = userPersistencePort;
        this.encryptionPersistencePort = encryptionPersistencePort;
        this.userValidation = userValidation;
    }

    @Override
    public void saveAuxBodegaUser(User user) {
        if (userPersistencePort.getAuxBodegaUserByDocument(user.getDocument()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.DOCUMENT_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getAuxBodegaUserByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getAuxBodegaUserByPhone(user.getPhone()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.PHONE_ALREADY_EXISTS_MESSAGE);
        }

        userValidation.validateUser(user);
        String encryptedPassword = encryptionPersistencePort.encodePassword(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.saveAuxBodegaUser(user);
    }
}
