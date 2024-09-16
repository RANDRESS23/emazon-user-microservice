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
    public static final String SAVE_AUX_BODEGA_USER_SUMMARY = "Register a new warehouse assistant";
    public static final String SAVE_AUX_BODEGA_USER_RESPONSE_201_DESCRIPTION = "Warehouse assistant has been registered";
    public static final String SAVE_AUX_BODEGA_USER_RESPONSE_400_DESCRIPTION = "Invalid param";

    public static final String SAVE_CLIENT_SUMMARY = "Register a new client";
    public static final String SAVE_CLIENT_RESPONSE_201_DESCRIPTION = "Client has been registered";
    public static final String SAVE_CLIENT_RESPONSE_400_DESCRIPTION = "Invalid param";

    public static final String TAG_AUTH_NAME = "Authentication";
    public static final String TAG_AUTH_DESCRIPTION = "Endpoints for user authentication";
    public static final String LOGIN_SUMMARY = "Search if the user Exists in database and give him a token";
    public static final String LOGIN_RESPONSE_RESPONSE_200_DESCRIPTION = "A token created with user information";
    public static final String LOGIN_ERROR_RESPONSE_400_DESCRIPTION = "User email or password is incorrect";
}
