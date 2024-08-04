package com.core.solution.model.payload;

import lombok.Data;

@Data
public class SignupRequest {

	private Integer userId;
	private String name;
	private String lastname;
	private String username;
	private String email;
	private Long phone;
	private String password;
	private Integer age;
	
}