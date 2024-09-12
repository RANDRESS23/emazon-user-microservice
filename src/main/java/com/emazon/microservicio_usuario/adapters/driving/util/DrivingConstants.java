package com.emazon.microservicio_usuario.adapters.driving.util;

public class DrivingConstants {
    private DrivingConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String USER_ID = "userId";
    public static final String USER_BIRTHDATE = "birthdate";
    public static final String FORMAT_FROM_STRING_TO_LOCAL_DATE = "formatFromStringToLocalDate";
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    public static final String RESPONSE_CODE_200="200";
    public static final String RESPONSE_CODE_201="201";
    public static final String RESPONSE_CODE_400="400";

    public static final String TAG_USER_NAME = "User Management";
    public static final String TAG_USER_DESCRIPTION = "Endpoints for managing users";
    public static final String SWAGGER_REGISTER_WAREHOUSE_ASSISTANT_SUMMARY = "Register a new warehouse assistant";
    public static final String SWAGGER_REGISTER_WAREHOUSE_ASSISTANT_RESPONSE = "Warehouse assistant has been registered";
    public static final String SWAGGER_REGISTER_USER_EMAIL_EXISTS = "User with that email already exists";
    public static final String SWAGGER_REGISTER_USER_IDENTITY_DOCUMENT_EXISTS = "User that identity document already exists";
    public static final String SWAGGER_REGISTER_USER_UNDERAGE = "User is under aged";
    public static final String SWAGGER_VALIDATIONS_DONT_PASS = "Some of the field doesn't pass validations";

    public static final String TAG_AUTH_NAME = "Authentication";
    public static final String TAG_AUTH_DESCRIPTION = "Endpoints for user authentication";
    public static final String SWAGGER_LOGIN_SUMMARY = "Search if the user Exists in database and give him a token";
    public static final String SWAGGER_LOGIN_RESPONSE = "A token created with user information";
    public static final String SWAGGER_LOGIN_ERROR = "User email or password is incorrect";
}
