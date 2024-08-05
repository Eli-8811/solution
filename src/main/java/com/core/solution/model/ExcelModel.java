package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelModel {

	private String nameFile;
	private List<SheetModel> listSheetModel;
	
}
