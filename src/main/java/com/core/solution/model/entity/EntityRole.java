package com.core.solution.model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityRole {

	private Integer roleId;
	private String name;
	private Boolean enabled; 
	private Date creationAt; 
	private Date modificationAt;
	
}
