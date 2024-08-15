package com.core.solution.model.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqAforeUpdate {

	@JsonProperty(index=0, value = "config")
	private ReqConfigTable reqConfigTable;
	
	@JsonProperty(index=1, value = "values")
	private List<ReqTableValue> listReqTableValue;
	
}
