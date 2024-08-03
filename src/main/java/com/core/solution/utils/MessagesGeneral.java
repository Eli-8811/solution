package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesGeneral {
	
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	
	public static final String TAG_GET = "get";
	public static final String TAG_POST = "post";
	public static final String TAG_PATCH = "patch";
	public static final String TAG_PUT = "put";
	public static final String TAG_DELETE = "delete";
	
	public static final String MAPPING_GET = "/get";
	public static final String MAPPING_GET_ALL = "/all";
	public static final String MAPPING_CREATE = "/create";
	public static final String MAPPING_PATCH = "/patch";
	public static final String MAPPING_PUT = "/put";
	public static final String MAPPING_DELETE = "/delete";
	
	public static final String MAPPING_ALL_DELETE = "/all/delete";

	public static final boolean SUCCESS_CREATE_USER = true;
	public static final String MESSAGE_CREATE_USER = "Created user with username %s successfully.";
	public static final String DATA_CREATE_USER = null;
	
	public static final boolean SUCCESS_GET_USER = true;
	public static final String MESSAGE_GET_USER = "Get user with username %s has been find successfully.";
	public static final String DATA_GET_USER = null;	
	
	public static final boolean SUCCESS_GET_ALL_USER = true;
	public static final String MESSAGE_GET_ALL_USER = "Get all users successfully.";
	public static final String DATA_GET_ALL_USER = null;	
	
	public static final boolean SUCCESS_DELETE_USER = true;
	public static final String MESSAGE_DELETE_USER = "Delete user with username %s successfully.";
	public static final String DATA_DELETE_USER = null;	

	public static final boolean SUCCESS_PATCH_USER = true;
	public static final String MESSAGE_PATCH_USER = "Patch user with username %s successfully.";
	public static final String DATA_PATCH_USER = null;	
	
	public static final boolean SUCCESS_PUT_USER = true;
	public static final String MESSAGE_PUT_USER = "Put user with username %s successfully.";
	public static final String DATA_PUT_USER = null;	
	
}