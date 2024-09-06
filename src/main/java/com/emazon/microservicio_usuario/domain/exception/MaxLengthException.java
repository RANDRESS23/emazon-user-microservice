package com.emazon.microservicio_usuario.domain.exception;

public class MaxLengthException extends RuntimeException {
    public MaxLengthException(String message) {
        super(message);
    }
}
