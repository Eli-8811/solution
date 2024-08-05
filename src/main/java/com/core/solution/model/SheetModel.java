package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class SheetModel {

	private String sheetName;
	private List<RowModel> listRowModel;
}
