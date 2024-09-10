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
}
