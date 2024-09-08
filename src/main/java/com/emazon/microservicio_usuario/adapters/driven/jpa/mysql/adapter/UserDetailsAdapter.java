package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.adapter;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByDocument(username)
                .orElseThrow(() -> new UsernameNotFoundException(DrivenConstants.USER_NOT_FOUND_MESSAGE));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        RoleEntity role = userEntity.getRole();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())));

        role.getPermissionList().forEach(permission ->
                authorityList.add(new SimpleGrantedAuthority(permission.getName().name()))
        );

        return new User(userEntity.getEmail(),
                userEntity.getPassword(),
                authorityList);
    }
}
