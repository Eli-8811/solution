package com.core.solution.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataException {

	private boolean success;	
	private String title;
	private String message;
	private Integer code;
	
}
