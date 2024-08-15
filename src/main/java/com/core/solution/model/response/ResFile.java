package com.core.solution.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResFile {

	@JsonProperty(index=1, value = "file_name")
	private String fileName;
	@JsonProperty(index=2, value = "file_size")
	private String fileZize;
	@JsonProperty(index=3, value = "absolute_path")
	private String absolutePath;
	@JsonProperty(index=4, value = "base64")
	private String base64;
	
}
