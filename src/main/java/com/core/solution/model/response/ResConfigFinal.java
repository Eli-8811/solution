package com.core.solution.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResConfigFinal {

	@JsonProperty(index=0, value = "config_id")
	private Integer configId;
	@JsonProperty(index=1, value = "type")
	private String type;
	@JsonProperty(index=2, value = "name_table")
	private String nameTable;
	@JsonProperty(index=3, value = "name_file")
	private String nameFile;
	@JsonProperty(index=4, value = "regimen")
	private String regimen;
	@JsonProperty(index=5, value = "enabled")
	private Boolean enabled;
	@JsonProperty(index=6, value = "creation_at")
	private Date creationAt;
	@JsonProperty(index=7, value = "modification_at")
	private Date modificationAt;
	
}