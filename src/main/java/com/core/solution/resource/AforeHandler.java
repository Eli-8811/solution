package com.core.solution.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.solution.bussines.AforeService;
import com.core.solution.model.entity.EntityConfigTable;
import com.core.solution.model.payload.ReqConfigParent;
import com.core.solution.model.response.ResConfigFinal;
import com.core.solution.model.response.ResConfigListFinal;
import com.core.solution.model.response.ResGeneric;
import com.core.solution.utils.MessagesResources;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/autoservice")
public class AforeHandler {

	private final AforeService aforeService;
	
	@SneakyThrows
	@GetMapping("/config")
	public ResponseEntity<ResGeneric<ResConfigListFinal>> getConfigTables() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResGeneric<ResConfigListFinal> responseGeneric = null;
		ResConfigListFinal resConfigListFinal = new ResConfigListFinal();				
		List<ResConfigFinal> listResConfigFinal = new ArrayList<ResConfigFinal>();
		
		for(EntityConfigTable dataEntity: this.aforeService.getConfigTables()) {
			
			ResConfigFinal resConfigFinal = new ResConfigFinal();
			resConfigFinal.setConfigId(dataEntity.getConfigId());
			resConfigFinal.setType(dataEntity.getType());
			resConfigFinal.setRegimen(dataEntity.getRegimen());
			resConfigFinal.setNameTable(dataEntity.getNameTable());
			resConfigFinal.setNameFile(dataEntity.getNameFile());
			resConfigFinal.setRowInit(dataEntity.getRowInit());
			resConfigFinal.setColumnInit(dataEntity.getColumnInit());
			resConfigFinal.setEnabled(dataEntity.getEnabled());
			resConfigFinal.setCreationAt(dataEntity.getCreationAt());
			resConfigFinal.setModificationAt(dataEntity.getModificationAt());
			listResConfigFinal.add(resConfigFinal);
			
		}
		
		resConfigListFinal.setListConfigTable(listResConfigFinal);
		
		responseGeneric = new ResGeneric<>(
				MessagesResources.SUCCESS, 
				MessagesResources.MESSAGE_GET_ALL_CONFIG_AUTO,
				resConfigListFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);	
	}
	
	@SneakyThrows
	@PatchMapping("/updates/table")
	public ResponseEntity<ResGeneric<String>> patchTable(@RequestBody ReqConfigParent reqConfigParent) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResGeneric<String> responseGeneric = null;
		
		this.aforeService.patchTable(reqConfigParent);
	
		responseGeneric = new ResGeneric<>(
				MessagesResources.SUCCESS, 
				MessagesResources.MESSAGE_UPDATE_AUTO_VALUES, 
				MessagesResources.DATA_NULL);
		
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
		
	}
	
	@SneakyThrows
	@PostMapping("/download/updates/table")
	public ResponseEntity<ResGeneric<String>> getDownloadTemplate(@RequestBody ReqConfigParent reqConfigParent) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResGeneric<String> responseGeneric = null;
		
		this.aforeService.getDownloadTemplate(reqConfigParent);
	
		responseGeneric = new ResGeneric<>(
				MessagesResources.SUCCESS, 
				MessagesResources.MESSAGE_UPDATE_AUTO_VALUES, 
				MessagesResources.DATA_NULL);
		
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
		
	}
	
}
