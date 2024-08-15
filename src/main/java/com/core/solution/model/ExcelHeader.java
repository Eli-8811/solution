package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelHeader {

	private Integer rowNumber;
	private Integer columnNumber;
	
	private List<String> listHeaderName;
	
}
