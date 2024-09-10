package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.microservicio_usuario.configuration.securityconfig.jwtconfiguration.JwtService;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.spi.IAuthPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;

    @Override
    public boolean validateCredentials(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);

        if (userEntity == null) return false;

        return passwordEncoder.matches(password, userEntity.getPassword());
    }

    @Override
    public User authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();

        return IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper);
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(generateExtraClaims(user), user);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        RoleEntity roleEntity = roleRepository.findByName(user.getRole().getName()).orElseThrow();
        extraClaims.put("userId", user.getUserId());
        extraClaims.put("role", roleEntity.getName());
        return extraClaims;
    }
}
