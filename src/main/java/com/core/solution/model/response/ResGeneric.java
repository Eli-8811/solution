package com.core.solution.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResGeneric<T> {
    
	private boolean success;
	private String message;
	private T data;

}