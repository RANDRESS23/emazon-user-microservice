package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = DrivenConstants.ROLES_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_ROLE_ID)
    private Long roleId;

    @Column(name = DrivenConstants.COLUMN_NAME, nullable = false, unique = true)
    private String name;

    @Column(name = DrivenConstants.COLUMN_ROLE_DESCRIPTION, nullable = false, length = DrivenConstants.MAXIMUM_ROLE_DESCRIPTION_CHARACTERS)
    private String description;
}
