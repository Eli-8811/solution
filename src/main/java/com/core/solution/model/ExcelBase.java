package com.core.solution.model;

import java.util.List;

import lombok.Data;

@Data
public class ExcelBase {

	private String nameFile;
	private List<ExcelSheet> listSheetModel;
	
}
