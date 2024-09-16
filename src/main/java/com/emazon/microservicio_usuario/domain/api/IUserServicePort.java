package com.emazon.microservicio_usuario.domain.api;

import com.emazon.microservicio_usuario.domain.model.User;

public interface IUserServicePort {
    String validateUser(User user);
    User saveAuxBodegaUser(User user);
    User saveClient(User user);
}
