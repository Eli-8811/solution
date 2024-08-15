package com.core.solution.model.payload;

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
public class ReqConfigTable {

	@JsonProperty(index=0, value = "config_id")
	private Integer configId;
	
	@JsonProperty(index=1, value = "type")
	private String type;
	
	@JsonProperty(index=2, value = "regimen")
	private String regimen;
	
	@JsonProperty(index=3, value = "name_table")
	private String nameTable;
	
	@JsonProperty(index=4, value = "name_file")
	private String nameFile;
	
	@JsonProperty(index=5, value = "row_init")
	private Integer rowInit;
	
	@JsonProperty(index=6, value = "column_init")
	private Integer columnInit;
	
	@JsonProperty(index=7, value = "enabled")
	private Boolean enabled;
	
	@JsonProperty(index=8, value = "creation_at")
	private Date creationAt;
	
	@JsonProperty(index=9, value = "modification_at")
	private Date modificationAt;
	
}
