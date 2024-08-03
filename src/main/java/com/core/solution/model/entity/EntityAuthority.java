package com.core.solution.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityAuthority {

	private Integer userId;
	private Integer authorityId;
	private String name;
	
}
