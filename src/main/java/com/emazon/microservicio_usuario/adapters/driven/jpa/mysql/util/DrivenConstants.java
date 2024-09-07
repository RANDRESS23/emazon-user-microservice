package com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.util;

public class DrivenConstants {
    private DrivenConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DOCUMENT_REGEX = "^\\d+$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    public static final int MAXIMUM_USER_NAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_LASTNAME_CHARACTERS = 50;
    public static final int MAXIMUM_USER_PHONE_CHARACTERS = 13;
    public static final int MAXIMUM_ROLE_DESCRIPTION_CHARACTERS = 200;

    public static final String USERS_TABLE_NAME = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_DOCUMENT = "document";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_BIRTHDATE = "birthdate";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_PERMISSION_ID = "permission_id";

    public static final String ROLES_TABLE_NAME = "role";
    public static final String COLUMN_ROLE_DESCRIPTION = "description";

    public static final String PERMISSIONS_TABLE_NAME = "permissions";
    public static final String COLUMN_PERMISSION_NAME = "name";

    public static final String DOCUMENT_ALREADY_EXISTS_MESSAGE = "Document already exists.";
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email already exists.";
    public static final String PHONE_ALREADY_EXISTS_MESSAGE = "Phone already exists.";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found.";

    public static final String INVALID_DOCUMENT = "The 'document' field is not valid";
    public static final String INVALID_PHONE = "The 'phone' field is not valid";
    public static final String INVALID_EMAIL = "The 'email' field is not valid";

    public static final String ROLE_PERMISSIONS_TABLE_NAME = "role_permissions";
}
