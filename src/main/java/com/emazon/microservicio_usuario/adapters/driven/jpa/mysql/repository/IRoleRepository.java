package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
