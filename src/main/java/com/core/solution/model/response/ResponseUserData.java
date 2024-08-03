package com.core.solution.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserData {

	@JsonProperty(index=0, value = "user_id")
	private Integer userId;
	
	@JsonProperty(index=1, value = "name")
	private String name;
	
	@JsonProperty(index=2, value = "lastname")
	private String lastname;
	
	@JsonProperty(index=3, value = "username")
	private String username;
	
	@JsonProperty(index=4, value = "email")
	private String email;
	
	@JsonProperty(index=5, value = "phone")
	private Long phone;
	
	@JsonProperty(index=6, value = "password")
	private String password;
	
	@JsonProperty(index=7, value = "age")
	private Integer age;
	
	@JsonProperty(index=8, value = "enabled")
	private Boolean enabled;
	
	@JsonProperty(index=9, value = "creation_at")
	private Date creationAt;
	
	@JsonProperty(index=10, value = "modification_at")
	private Date modificationAt;
	
}
