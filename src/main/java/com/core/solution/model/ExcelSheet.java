package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelSheet {

	private String sheetName;
	private List<ExcelRow> listRowModel;
}
