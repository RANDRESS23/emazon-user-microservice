package com.emazon.microservicio_usuario.domain.spi;

public interface IEncryptionPersistencePort {
    String encodePassword(String password);
}
