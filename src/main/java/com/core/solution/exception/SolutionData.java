package com.core.solution.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolutionData implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean success;	
	private String title;
	private String message;
	private Integer code;
	
}
