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
public class EntityConfigTable {

	private Integer configId;
	private String type;
	private String regimen;
	private String nameTable;
	private String nameFile;
	private Integer rowInit;
	private Integer columnInit;
	private Boolean enabled;
	private Date creationAt;
	private Date modificationAt;
	
}
