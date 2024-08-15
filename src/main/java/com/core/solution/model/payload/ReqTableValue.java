package com.core.solution.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqTableValue {

	@JsonProperty(index=0, value = "origen_id")
	private Integer origenid;

	@JsonProperty(index=1, value = "detail_id")
	private Integer detailId;

	@JsonProperty(index=2, value = "column_table")
	private String columnTable;
	
	@JsonProperty(index=3, value = "row_value")
	private String rowValue;
	
	@JsonProperty(index=4, value = "version_id")
	private Integer versionId;
	
	@JsonProperty(index=5, value = "is_approvate")
	private Integer isApprovate;
	
}
