package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class RowModel {

	private Integer rowNumber;
	private List<CellModel> listCellModel;
	
	
}
