package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.PermissionEntity;
import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByName(PermissionEnum name);
}
