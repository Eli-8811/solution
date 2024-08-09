package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesResources {

	public static final String MAPPING_GET = "/get";
	public static final String MAPPING_GET_ALL = "/all";
	public static final String MAPPING_CREATE = "/create";
	public static final String MAPPING_PATCH = "/patch";
	public static final String MAPPING_PUT = "/put";
	public static final String MAPPING_DELETE = "/delete";	

	public static final boolean SUCCESS = true;
	public static final String DATA_NULL = null;			
	
	public static final String MESSAGE_CREATE_USER = "Created user with username %s successfully.";	
	public static final String MESSAGE_GET_USER = "Get user with username %s has been find successfully.";
	public static final String MESSAGE_GET_ALL_USER = "Get all users successfully.";
	public static final String MESSAGE_DELETE_USER = "Delete user with username %s successfully.";
	public static final String MESSAGE_PATCH_USER = "Patch user with username %s successfully.";
	public static final String MESSAGE_PUT_USER = "Put user with username %s successfully.";
	public static final String MESSAGE_REPORT_RANGE_USERS = "Reporte de usuarios por fecha de creacion desde %s hasta %s.";
	
}