package com.core.solution.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.bussines.ExcelService;
import com.core.solution.model.ExcelModel;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/excel")
public class ExcelHandler {

	private final ExcelService excelService;
	
	@PostMapping(value = "big/read", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadBigFile(@RequestPart(value = "file", required = false ) MultipartFile file) {
		
		ExcelModel excelModel = this.excelService.uploadBigFile(file);
		log.info("File name file {} ", excelModel.getNameFile());
		
	}
	
	@SneakyThrows
	@GetMapping("/rewrite")
	public String rewriteExcelReportUsers() {				
		return this.excelService.rewriteExcelReportUsers();
	}
	
}