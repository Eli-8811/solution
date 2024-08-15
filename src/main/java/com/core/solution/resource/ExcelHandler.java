package com.core.solution.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.solution.bussines.ExcelService;
import com.core.solution.model.ExcelModel;
import com.core.solution.model.response.ResFile;
import com.core.solution.model.response.ResFileFinal;
import com.core.solution.model.response.ResGeneric;
import com.core.solution.utils.MessagesResources;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/excel")
public class ExcelHandler {

	private final ExcelService excelService;

	@SneakyThrows
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "big/read", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadBigFile(@RequestPart(value = "file", required = false) MultipartFile file) {

		ExcelModel excelModel = this.excelService.uploadBigFile(file);
		log.info("File name file {} ", excelModel.getNameFile());

	}

	@SneakyThrows
	@GetMapping("/rewrite")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResGeneric<ResFileFinal>> rewriteExcelReportUsers(
			@RequestParam("datetimeStart") String datetimeStart, 
			@RequestParam("datetimeEnd") String datetimeEnd) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResGeneric<ResFileFinal> responseGeneric = null;
		ResFileFinal responseFileFinal = new ResFileFinal();
		ResFile responseFile = this.excelService.rewriteExcelReportUsers(datetimeStart, datetimeEnd);
		responseFileFinal.setResponseFile(responseFile);
		responseGeneric = new ResGeneric<>(
				MessagesResources.SUCCESS, 
				String.format(MessagesResources.MESSAGE_REPORT_RANGE_USERS, datetimeStart, datetimeEnd), 
				responseFileFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	@SneakyThrows
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "upload/afore", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadFileComisionAfore(@RequestPart(value = "file", required = false) MultipartFile file) {
		this.excelService.uploadFileComisionAfore(file);
	}
	
}