package com.core.solution.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.bussines.ExcelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/excel")
public class ExcelHandler {

	private final ExcelService excelService;
	
	
	@PostMapping("/xlsx")
	@ResponseBody
	public ResponseEntity<?> uploadBigFile(@RequestParam("file") MultipartFile file) {		
		try {
			log.info("File size {} ", file.getSize());
			log.info("File name {} ", file.getName());			
			log.info("File original name {} ", file.getOriginalFilename());
			this.excelService.uploadBigFile(file);
		} catch (Exception e) {			                                            
			e.printStackTrace();
		}
		return null;
	}
	
}