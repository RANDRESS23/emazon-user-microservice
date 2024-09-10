package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = DrivenConstants.ROLES_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_ROLE_ID)
    private Long roleId;

    @Column(name = DrivenConstants.COLUMN_NAME, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(name = DrivenConstants.COLUMN_ROLE_DESCRIPTION, nullable = false, length = DrivenConstants.MAXIMUM_ROLE_DESCRIPTION_CHARACTERS)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = DrivenConstants.ROLE_PERMISSIONS_TABLE_NAME, joinColumns = @JoinColumn(name = DrivenConstants.COLUMN_ROLE_ID), inverseJoinColumns = @JoinColumn(name = DrivenConstants.COLUMN_PERMISSION_ID))
    private Set<PermissionEntity> permissionList;
}
