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
	
	public static final String TITLE_ERROR_USERNAME = "El username incorrecto.";
	public static final String MESSAGE_ERROR_USERNAME = "El username solo admite letras.";
	public static final Integer CODE_ERROR_USERNAME = 2006;
	
	public static final String TITLE_ERROR_CREATE_PASSWORD = "La contraseña incorrecta.";
	public static final String MESSAGE_ERROR_CREATE_PASSWORD = "La contraseña debe ser mayor igual a 8 de longitud, una mayuscula, una minuscula, un numero y cualquiera de los siguientes caracteres especiales, (.*[!@#$%^&*()_+\\\\-=\\\\[\\\\]{};':\\\"\\\\\\\\|,.<>\\\\/?].*) ";
	public static final Integer CODE_ERROR_CREATE_PASSWORD = 2007;
	
	public static final String TITLE_ERROR_PHONE = "El telefono incorrecto.";
	public static final String MESSAGE_ERROR_PHONE = "El telefono debe contener 10 digitos y solamente debe contener numeros.";
	public static final Integer CODE_ERROR_PHONE = 2008;
	
	public static final String TITLE_ERROR_EMAIL = "Correo incorrecto.";
	public static final String MESSAGE_ERROR_EMAIL = "El correo debe contener el formato correcto.";
	public static final Integer CODE_ERROR_EMAIL = 1009;
	
	public static final String TITLE_ERROR_PROCESS_EXCEL_NORMAL = "Procesamiento con errrores.";
	public static final String MESSAGE_ERROR_PROCESS_EXCEL_NORMAL = "El procesamiento del excel se ha ejecutado con errores.";
	public static final Integer CODE_ERROR_PROCESS_EXCEL_NORMAL = 1010;
	
	public static final String TITLE_ERROR_GENERATE_B64 = "No se pudo generar el base 64..";
	public static final String MESSAGE_ERROR_GENERATE_B64 = "El base64 no logro generarse para el archivo %s.";
	public static final Integer CODE_ERROR_GENERATE_B64 = 1011;
	
	public static final String TITLE_ERROR_GET_ALL_CONFIG_AUTO = "Sin resultado.";
	public static final String MESSAGE_ERROR_GET_ALL_CONFIG_AUTO = "No se pudo hacer la consulta general de configuracion autoservicio.";
	public static final Integer CODE_ERROR_GET_ALL_CONFIG_AUTO = 2009;
	
	
}