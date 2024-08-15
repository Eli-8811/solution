package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelRowModel {

	private Integer rowNumber;
	private List<ExcelCellModel> listCellModel;
	
}
