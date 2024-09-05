package com.emazon.microservicio_usuario.domain.validation;

import com.emazon.microservicio_usuario.domain.exception.EmptyFieldException;
import com.emazon.microservicio_usuario.domain.exception.InvalidFieldException;
import com.emazon.microservicio_usuario.domain.model.User;
import com.emazon.microservicio_usuario.domain.util.DomainConstants;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidation {
    public void validateUser(User user) {
        validateNameUser(user.getName());
        validateLastNameUser(user.getLastName());
        validateDocumentUser(user.getDocument());
        validateEmailUser(user.getEmail());
        validatePhoneUser(user.getPhone());
        validatePasswordUser(user.getPassword());
        validateAgeUser(user.getBirthdate());
    }

    private void validateNameUser(String name) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
    }

    private void validateLastNameUser(String lastName) {
        if (lastName.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.LASTNAME.toString());
        }
    }

    private void validateDocumentUser(String document) {
        if (document.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DOCUMENT.toString());
        }

        if (!Pattern.matches(DomainConstants.DOCUMENT_REGEX, document)) {
            throw new InvalidFieldException(DomainConstants.INVALID_DOCUMENT);
        }
    }

    private void validateEmailUser(String email) {
        if (email.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.EMAIL.toString());
        }

        if (!Pattern.matches(DomainConstants.EMAIL_REGEX, email)) {
            throw new InvalidFieldException(DomainConstants.INVALID_EMAIL);
        }
    }

    private void validatePhoneUser(String phone) {
        if (phone.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.PHONE.toString());
        }

        if (!Pattern.matches(DomainConstants.PHONE_REGEX, phone)) {
            throw new InvalidFieldException(DomainConstants.INVALID_PHONE);
        }
    }

    private void validatePasswordUser(String password) {
        if (password.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.PASSWORD.toString());
        }
    }

    private void validateAgeUser(LocalDate birthdate) {
        if (Period.between(birthdate, LocalDate.now()).getYears() < DomainConstants.MINIMUM_AGE_USER) {
            throw new InvalidFieldException(DomainConstants.INVALID_AGE);
        }
    }
}
