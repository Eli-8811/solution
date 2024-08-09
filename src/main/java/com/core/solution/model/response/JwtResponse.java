package com.core.solution.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	@JsonProperty(index=0, value = "user_id")
	private Integer userId;
	@JsonProperty(index=1, value = "username")
	private String username;
	@JsonProperty(index=2, value = "email")
	private String email;
	@JsonProperty(index=3, value = "phone")
	private Long phone;
	@JsonProperty(index=4, value = "roles")
	private List<String> roles;
	@JsonProperty(index=5, value = "token")
	private String token;
	
}