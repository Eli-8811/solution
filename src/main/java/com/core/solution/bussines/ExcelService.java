package com.core.solution.bussines;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.model.CellModel;
import com.core.solution.model.ExcelModel;
import com.core.solution.model.RowModel;
import com.core.solution.model.SheetModel;
import com.core.solution.utils.MemoryUtil;
import com.monitorjbl.xlsx.StreamingReader;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExcelService {

	public ExcelModel uploadBigFile(MultipartFile file) {
		MemoryUtil.showMemoryStats();
		long init = MemoryUtil.tiempoInicial();
		log.info("Time init {} ", init);		
		InputStream is = null;
		ExcelModel excelModel = new ExcelModel();
		try {
			is = file.getInputStream();
			Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(8192).open(is);
			List<SheetModel> listSheetModel = new ArrayList<SheetModel>();
			for (Sheet sheet : wb) {
				List<RowModel> listRowModel = new ArrayList<RowModel>();
				SheetModel sheetModel = new SheetModel();
				sheetModel.setSheetName(sheet.getSheetName());
				listSheetModel.add(sheetModel);
				log.info("Process sheet {} ", sheetModel.getSheetName());
				for (Row row : sheet) {
					List<CellModel> listCellModel = new ArrayList<CellModel>();
					RowModel rowModel = new RowModel();
					rowModel.setRowNumber(row.getRowNum());
					listRowModel.add(rowModel);
					for (Cell cell : row) {
						CellModel cellModel = new CellModel();
						cellModel.setCellValue(cell.getStringCellValue());
						listCellModel.add(cellModel);
					}
					rowModel.setListCellModel(listCellModel);
				}
				sheetModel.setListRowModel(listRowModel);
			}
			excelModel.setNameFile(file.getOriginalFilename());
			excelModel.setListSheetModel(listSheetModel);
			MemoryUtil.showMemoryStats();
			long end = MemoryUtil.tiempoFinal(init);
			log.info("Time end {} ", end);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return excelModel;
	}

	public String getNameSheets(MultipartFile file) {
		InputStream is = null;
		Workbook workbook = null;
		try {
			is = file.getInputStream();
			workbook = WorkbookFactory.create(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook.getSheetName(0) + "|" + workbook.getSheetName(1) + "|" + workbook.getSheetName(2);
	}

}
