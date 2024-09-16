package com.emazon.microservicio_usuario.configuration;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter.*;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.microservicio_usuario.configuration.securityconfig.jwtconfiguration.JwtService;
import com.emazon.microservicio_usuario.domain.api.IAuthServicePort;
import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.api.IUserServicePort;
import com.emazon.microservicio_usuario.domain.api.usecase.AuthUseCase;
import com.emazon.microservicio_usuario.domain.api.usecase.RoleUseCase;
import com.emazon.microservicio_usuario.domain.api.usecase.UserUseCase;
import com.emazon.microservicio_usuario.domain.spi.IAuthPersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IEncryptionPersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IRolePersistencePort;
import com.emazon.microservicio_usuario.domain.spi.IUserPersistencePort;
import com.emazon.microservicio_usuario.domain.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IEncryptionPersistencePort encryptionPersistencePort() {
        return new EncryptionAdapter(passwordEncoder);
    }

    @Bean
    public UserValidation userValidation() {
        return new UserValidation();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), roleServicePort(), encryptionPersistencePort(), userValidation());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthAdapter(userRepository, roleRepository, passwordEncoder, authenticationManager, roleEntityMapper, jwtService);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort());
    }
}
