package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesBussines {

	public static final boolean SUCCESS = false;

	public static final String TITLE_ERROR_GET_ALL_USER = "Sin resultado.";
	public static final String MESSAGE_ERROR_GET_ALL_USER = "No se pudo hacer la consulta general de usuarios.";
	public static final Integer CODE_ERROR_GET_ALL_USER = 2001;
	
	public static final String TITLE_NULL_USERNAME = "Sin resultado.";
	public static final String MESSAGE_NULL_USERNAME = "No se encontro el usuario por medio del username %s";
	public static final Integer CODE_NULL_USERNAME = 2002;
	
	public static final String TITLE_ERROR_PATCH_USER = "No se pudo parchar.";
	public static final String MESSAGE_ERROR_PATCH_USER = "No se pudo parchar el usuario por medio del username %s";
	public static final Integer CODE_ERROR_PATCH_USER = 2003;
	
	public static final String TITLE_ERROR_PUT_USER = "No se pudo actualizar.";
	public static final String MESSAGE_ERROR_PUT_USER = "No se pudo actualizar el usuario por medio del username %s";
	public static final Integer CODE_ERROR_PUT_USER = 2004;
	
	public static final String TITLE_ERROR_DELETE_USER = "No pudo borrar.";
	public static final String MESSAGE_ERROR_DELETE_USER = "No se pudo borrar el usuario por medio del username %s";
	public static final Integer CODE_ERROR_DELETE_USER = 2005;
	
	public static final String TITLE_ERROR_USERNAME = "Username incorrecto.";
	public static final String MESSAGE_ERROR_USERNAME = "El nombre de usuario solo admite letras.";
	public static final Integer CODE_ERROR_USERNAME = 2006;
	
	public static final String TITLE_ERROR_CREATE_PASSWORD = "Contraseña incorrecta.";
	public static final String MESSAGE_ERROR_CREATE_PASSWORD = "La contraseña debe ser mayor igual a 8 de longitud, una mayuscula, una minuscula, un numero y cualquiera de los siguientes caracteres especiales, (.*[!@#$%^&*()_+\\\\-=\\\\[\\\\]{};':\\\"\\\\\\\\|,.<>\\\\/?].*) ";
	public static final Integer CODE_ERROR_CREATE_PASSWORD = 2007;
	
	public static final String TITLE_ERROR_PHONE = "Telefono incorrecto.";
	public static final String MESSAGE_ERROR_PHONE = "El telefono debe contener 10 digitos y solamente debe contener numeros.";
	public static final Integer CODE_ERROR_PHONE = 2008;
	
	public static final String TITLE_ERROR_EMAIL = "Correo incorrecto.";
	public static final String MESSAGE_ERROR_EMAIL = "El correo debe contener el formato correcto.";
	public static final Integer CODE_ERROR_EMAIL = 1009;
	
}