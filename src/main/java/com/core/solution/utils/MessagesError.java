package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesError {

	public static final boolean SUCCESS_LOAD_DATABASE = false;
	public static final String TITLE_LOAD_DATABASE = "Error";
	public static final String MESSAGE_LOAD_DATABASE = "No se ha ejecutado la cunsulta sql correctamente.";
	
	public static final boolean SUCCESS_LOAD_BY_USERNAME = false;
	public static final String TITLE_LOAD_BY_USERNAME = "Error";
	public static final String MESSAGE_LOAD_BY_USERNAME = "Error al obtener el usuario por medio del username: %s";
	
}