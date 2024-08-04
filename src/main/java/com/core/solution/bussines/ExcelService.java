package com.core.solution.bussines;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.utils.MemoryUtil;
import com.monitorjbl.xlsx.StreamingReader;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExcelService {

	public void uploadBigFile(MultipartFile file) throws IOException {
	
		long init = MemoryUtil.tiempoInicial();
		log.info("Time init {} ", init);
		MemoryUtil.showMemoryStats();		
		InputStream is = file.getInputStream();
		Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(8192).open(is);	
		for (Sheet sheet : wb) {
			//log.info("Reading sheet ".concat(sheet.getSheetName()));
			for (Row row : sheet) {
				//log.info("Reading row ".concat(String.valueOf(row.getRowNum())));
				for (Cell cell : row) {
					//log.info("Reading cell ".concat(cell.getStringCellValue()));
					log.info(sheet.getSheetName().concat(" row ".concat(String.valueOf(row.getRowNum()).concat(" cell ".concat(cell.getStringCellValue())))));
				}
			}
		}		
		MemoryUtil.showMemoryStats();
		long end = MemoryUtil.tiempoFinal(init);
		log.info("Time end {} ", end);
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
