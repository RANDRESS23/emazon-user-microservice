package com.emazon.microservicio_usuario.domain.api.usecase;

import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import com.emazon.microservicio_usuario.domain.exception.AlreadyExistsFieldException;
import com.emazon.microservicio_usuario.domain.model.Role;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IEncryptionPersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;
import com.emazon.microservicio_usuario.domain.validation.UserValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRoleServicePort roleServicePort;
    private final IEncryptionPersistencePort encryptionPersistencePort;
    private final UserValidation userValidation;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRoleServicePort roleServicePort, IEncryptionPersistencePort encryptionPersistencePort, UserValidation userValidation) {
        this.userPersistencePort = userPersistencePort;
        this.roleServicePort = roleServicePort;
        this.encryptionPersistencePort = encryptionPersistencePort;
        this.userValidation = userValidation;
    }

    @Override
    public String validateUser(User user) {
        if (userPersistencePort.getUserByDocument(user.getDocument()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.DOCUMENT_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getUserByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }

        if (userPersistencePort.getUserByPhone(user.getPhone()).isPresent()) {
            throw new AlreadyExistsFieldException(DomainConstants.PHONE_ALREADY_EXISTS_MESSAGE);
        }

        userValidation.validateUser(user);

        return encryptionPersistencePort.encodePassword(user.getPassword());
    }

    @Override
    public User saveAuxBodegaUser(User user) {
        String encryptedPassword = validateUser(user);
        Role role = roleServicePort.getRole(RoleEnum.AUX_BODEGA);
        user.setRole(role);
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }

    @Override
    public User saveClient(User user) {
        String encryptedPassword = validateUser(user);
        Role role = roleServicePort.getRole(RoleEnum.CLIENTE);
        user.setRole(role);
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }
}
