package com.core.solution.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private String token;
	private Integer userId;
	private String username;
	private String email;
	private Long phone;
	private List<String> roles;
	
}