package com.emazon.microservicio_usuario.domain.api;

public interface IAuthServicePort {
    String login(String email, String password);
}
