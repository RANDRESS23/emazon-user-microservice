package com.emazon.microservicio_usuario.domain.model;

import com.emazon.microservicio_usuario.domain.exception.EmptyFieldException;
import com.emazon.microservicio_usuario.domain.exception.InvalidFieldException;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class User {
    private final Long userId;
    private final String name;
    private final String lastName;
    private final String document;
    private final String phone;
    private final LocalDate birthdate;
    private final String email;
    private String password;
    private Role role;

    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.name = requireNonNull(builder.name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.lastName = requireNonNull(builder.lastName, DomainConstants.FIELD_LASTNAME_NULL_MESSAGE);
        this.document = requireNonNull(builder.document, DomainConstants.FIELD_DOCUMENT_NULL_MESSAGE);
        this.phone = requireNonNull(builder.phone, DomainConstants.FIELD_PHONE_NULL_MESSAGE);
        this.birthdate = requireNonNull(builder.birthdate, DomainConstants.FIELD_BIRTHDATE_NULL_MESSAGE);
        this.email = requireNonNull(builder.email, DomainConstants.FIELD_EMAIL_NULL_MESSAGE);
        this.password = requireNonNull(builder.password, DomainConstants.FIELD_PASSWORD_NULL_MESSAGE);
        this.role = requireNonNull(builder.role, DomainConstants.FIELD_ROLE_NULL_MESSAGE);
    }

    public static class UserBuilder {
        private Long userId;
        private String name;
        private String lastName;
        private String document;
        private String phone;
        private LocalDate birthdate;
        private String email;
        private String password;
        private Role role;

        public UserBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder document(String document) {
            this.document = document;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
