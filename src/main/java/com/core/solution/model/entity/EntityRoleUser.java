package com.core.solution.model.entity;

import java.util.Date;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityRoleUser {

	private Integer roleUserId;
	private Integer userId;
	private Integer roleId;
	private String name;
	private Boolean enabled; 
	private Date creationAt; 
	private Date modificationAt;
	
}
