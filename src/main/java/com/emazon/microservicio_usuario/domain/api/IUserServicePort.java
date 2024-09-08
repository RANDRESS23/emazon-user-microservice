package com.emazon.microservicio_usuario.domain.api;

import com.emazon.microservicio_usuario.domain.model.User;

public interface IUserServicePort {
    void saveAuxBodegaUser(User user);
}
