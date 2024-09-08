package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = DrivenConstants.PERMISSIONS_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(name = DrivenConstants.COLUMN_PERMISSION_NAME, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;
}
