package com.core.solution.model.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest {

	private Integer userId;
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String lastname;
	@NotBlank
	@NotNull
	private String username;
	@NotBlank
	@NotNull
	private String email;
	@NotBlank
	@NotNull
	private Long phone;
	@NotBlank
	@NotNull
	private String password;
	@NotBlank
	@NotNull
	@Size(min = 1, max = 100)
	private Integer age;
	
}