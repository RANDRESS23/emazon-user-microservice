package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util.DrivenConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = DrivenConstants.USERS_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DrivenConstants.COLUMN_USER_ID)
    private Long userId;

    @Column(name = DrivenConstants.COLUMN_NAME, nullable = false, length = DrivenConstants.MAXIMUM_USER_NAME_CHARACTERS)
    private String name;

    @Column(name = DrivenConstants.COLUMN_USER_LASTNAME, nullable = false, length = DrivenConstants.MAXIMUM_USER_LASTNAME_CHARACTERS)
    private String lastName;

    @Column(name = DrivenConstants.COLUMN_USER_DOCUMENT, nullable = false, unique = true)
    @Pattern(regexp = DrivenConstants.DOCUMENT_REGEX, message = DrivenConstants.INVALID_DOCUMENT)
    private String document;

    @Column(name = DrivenConstants.COLUMN_USER_PHONE, nullable = false, unique = true, length = DrivenConstants.MAXIMUM_USER_PHONE_CHARACTERS)
    @Pattern(regexp = DrivenConstants.PHONE_REGEX, message = DrivenConstants.INVALID_PHONE)
    private String phone;

    @Column(name = DrivenConstants.COLUMN_USER_BIRTHDATE, nullable = false)
    @Pattern(regexp = DrivenConstants.BIRTHDATE_REGEX, message = DrivenConstants.INVALID_BIRTHDATE)
    private String birthdate;

    @Column(name = DrivenConstants.COLUMN_USER_EMAIL, nullable = false, unique = true)
    @Pattern(regexp = DrivenConstants.EMAIL_REGEX, message = DrivenConstants.INVALID_EMAIL)
    private String email;

    @Column(name = DrivenConstants.COLUMN_USER_PASSWORD, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = DrivenConstants.COLUMN_ROLE_ID)
    private RoleEntity role;
}
