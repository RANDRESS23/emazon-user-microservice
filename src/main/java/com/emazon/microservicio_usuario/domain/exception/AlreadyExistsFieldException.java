package com.emazon.microservicio_usuario.domain.exception;

public class AlreadyExistsFieldException extends RuntimeException {
    public AlreadyExistsFieldException(String message) {
        super(message);
    }
}
