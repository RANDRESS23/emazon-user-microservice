package com.emazon.microservicio_usuario.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "%s was not found";
    public static final String ALREADY_EXISTS_EXCEPTION_MESSAGE = "The %s field already exists.";
    public static final String MAXIMUM_CHARACTERS_EXCEPTION_MESSAGE = "The %s field has reached its maximum character size";

    public static final String DESCRIPTION_ROLE_ADMIN = "Usuario con permisos completos para gestionar el sistema, incluyendo la administración de usuarios, roles y configuraciones críticas.";
    public static final String DESCRIPTION_ROLE_AUX_BODEGA = "Responsable de gestionar y organizar el inventario en bodega, asegurando la correcta recepción, almacenamiento y despacho de productos.";
    public static final String DESCRIPTION_ROLE_CLIENTE = "Usuario que puede navegar, seleccionar y comprar productos, además de gestionar sus pedidos y datos personales.";
}
