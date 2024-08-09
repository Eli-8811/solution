package com.core.solution.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Constants {
	
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final int MIN_LENGTH = 8;
	public static final String LOWERCASE_PATTERN = "(.*[a-z].*)";
	public static final String UPPERCASE_PATTERN = "(.*[A-Z].*)";
	public static final String DIGIT_PATTERN = "(.*[0-9].*)";
	public static final String SPECIAL_CHARACTER_PATTERN = "(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*)";       
	public static final String LETTERS_ONLY_PATTERN = "^[a-zA-Z]+$";
	public static final String PHONE_PATTERN = "^\\d{10}$";
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	public static final String REPORT_RANGE_DATE_USERS = "/excel/template_report_users.xlsx";
	
	
	
}