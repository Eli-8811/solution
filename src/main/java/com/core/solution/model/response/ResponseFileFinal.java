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
public class ResponseFileFinal {

	@JsonProperty(index=0, value = "file")
	private ResponseFile responseFile;
	
}
