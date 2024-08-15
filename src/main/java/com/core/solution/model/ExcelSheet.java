package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelSheetModel {

	private String sheetName;
	private List<ExcelRowModel> listRowModel;
}
