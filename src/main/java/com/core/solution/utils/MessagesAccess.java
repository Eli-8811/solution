package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesAccess {

	public static final boolean SUCCESS = false;
	
	public static final String TITLE_ERROR_DB_GET_ALL_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_GET_ALL_USER = "Error en la busqueda general.";
	public static final Integer CODE_ERROR_DB_GET_ALL_USER = 3001;
	
	public static final String TITLE_ERROR_DB_GET_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_GET_USER = "Error al buscar el usuario %s.";
	public static final Integer CODE_ERROR_DB_GET_USER = 3002;
	
	public static final String TITLE_ERROR_DB_PATCH_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_PATCH_USER = "Error al parchar el usuario %s.";
	public static final Integer CODE_ERROR_DB_PATCH_USER = 3003;
	
	public static final String TITLE_ERROR_DB_PUT_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_PUT_USER = "Error al actualizar el usuario %s.";
	public static final Integer CODE_ERROR_DB_PUT_USER = 3004;
	
	public static final String TITLE_ERROR_DB_DELETE_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_DELETE_USER = "Error al borrar el usuario %s.";
	public static final Integer CODE_ERROR_DB_DELETE_USER = 3005;	
	
	public static final String TITLE_ERROR_DB_CRATE_USER = "Error en el servidor.";
	public static final String MESSAGE_ERROR_DB_CRATE_USER = "Creacion de usuario incorrecta.";
	public static final Integer CODE_ERROR_DB_CRATE_USER = 3006;
	
}