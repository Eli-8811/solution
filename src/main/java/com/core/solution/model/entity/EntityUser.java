package com.core.solution.model.entity;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityUser {

	private Integer userId;
	private String name;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private Integer age;
	private Long phone;
	private Boolean enabled; 
	private Date creationAt; 
	private Date modificationAt;
	
	Set<EntityRoleUser> roles;
	
}