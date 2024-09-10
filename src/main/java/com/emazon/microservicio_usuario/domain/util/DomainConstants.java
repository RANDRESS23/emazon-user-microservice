package com.emazon.microservicio_usuario.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        LASTNAME,
        DOCUMENT,
        PHONE,
        EMAIL,
        PASSWORD,
    }

    public static final String DOCUMENT_REGEX = "^\\d{7,10}$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,12}$";
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    public static final int MINIMUM_AGE_USER = 18;
    public static final int MAXIMUM_USER_NAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_LASTNAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_PHONE_CHARACTERS = 13;

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_LASTNAME_NULL_MESSAGE = "Field 'last name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DOCUMENT_NULL_MESSAGE = "Field 'document' cannot be null";
    public static final String FIELD_PHONE_NULL_MESSAGE = "Field 'phone' cannot be null";
    public static final String FIELD_BIRTHDATE_NULL_MESSAGE = "Field 'birthdate' cannot be null";
    public static final String FIELD_EMAIL_NULL_MESSAGE = "Field 'email' cannot be null";
    public static final String FIELD_PASSWORD_NULL_MESSAGE = "Field 'password' cannot be null";

    public static final String ROLE_NOT_FOUND = "Role not found.";

    public static final String MAXIMUM_USER_NAME_CHARACTERS_MESSAGE = "User name can have up to 50 characters maximum.";
    public static final String MAXIMUM_USER_LASTNAME_CHARACTERS_MESSAGE = "User lastname can have up to 50 characters maximum.";
    public static final String MAXIMUM_USER_PHONE_CHARACTERS_MESSAGE = "User phone can have up to 13 characters maximum.";

    public static final String INVALID_DOCUMENT = "The 'document' field is not valid";
    public static final String INVALID_PHONE = "The 'phone' field is not valid";
    public static final String INVALID_EMAIL = "The 'email' field is not valid";
    public static final String INVALID_AGE = "The 'birthdate' field is not valid, you must be of legal age";

    public static final String DOCUMENT_ALREADY_EXISTS_MESSAGE = "Document already exists.";
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email already exists.";
    public static final String PHONE_ALREADY_EXISTS_MESSAGE = "Phone already exists.";

    public static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials.";
}
