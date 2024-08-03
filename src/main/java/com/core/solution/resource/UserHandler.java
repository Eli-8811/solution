package com.core.solution.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.solution.bussines.UserService;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.UserRequest;
import com.core.solution.model.response.ResponseGeneric;
import com.core.solution.model.response.ResponseUserData;
import com.core.solution.model.response.ResponseUserFinal;
import com.core.solution.model.response.ResponseUserListFinal;
import com.core.solution.utils.MessagesGeneral;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserHandler {

	private final UserService userService;

	@PostMapping(MessagesGeneral.MAPPING_CREATE)
	public ResponseEntity<ResponseGeneric<ResponseUserFinal>> createUser(@RequestBody @Valid UserRequest userRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseUserData responseUserData = new ResponseUserData();
		ResponseUserFinal responseUserFinal = new ResponseUserFinal();
		ResponseGeneric<ResponseUserFinal> responseGeneric = null;
		this.userService.createUser(userRequest);
		responseUserData.setUserId(userRequest.getUserId());
		responseUserData.setName(userRequest.getName());
		responseUserData.setLastname(userRequest.getLastname());
		responseUserData.setUsername(userRequest.getUsername());
		responseUserData.setEmail(userRequest.getEmail());
		responseUserData.setPhone(userRequest.getPhone());
		responseUserData.setPassword(userRequest.getPassword());
		responseUserData.setAge(userRequest.getAge());
		responseUserData.setEnabled(true);
		responseUserData.setCreationAt(new Date());
		responseUserData.setModificationAt(new Date());
		responseUserFinal.setResponseUserData(responseUserData);
		responseGeneric = new ResponseGeneric<>(
				MessagesGeneral.SUCCESS_CREATE_USER,
				String.format(MessagesGeneral.MESSAGE_CREATE_USER, userRequest.getUsername()), 
				responseUserFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.CREATED);	
	}
	
	@GetMapping(MessagesGeneral.MAPPING_GET)
	public ResponseEntity<ResponseGeneric<ResponseUserFinal>> getUser(@RequestParam("username") String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseUserFinal responseUserFinal = new ResponseUserFinal();
		ResponseUserData responseUserData = new ResponseUserData();		
		ResponseGeneric<ResponseUserFinal> responseGeneric = null;
		EntityUser entityUser = this.userService.getUser(username.trim());			
		responseUserData.setUserId(entityUser.getUserId());
		responseUserData.setName(entityUser.getName());
		responseUserData.setLastname(entityUser.getLastname());
		responseUserData.setUsername(entityUser.getUsername());
		responseUserData.setEmail(entityUser.getEmail());
		responseUserData.setPassword(entityUser.getPassword());
		responseUserData.setAge(entityUser.getAge());
		responseUserData.setPhone(entityUser.getPhone());
		responseUserData.setEnabled(entityUser.getEnabled());
		responseUserData.setCreationAt(entityUser.getCreationAt());
		responseUserData.setModificationAt(entityUser.getModificationAt());			
		responseUserFinal.setResponseUserData(responseUserData);
		responseGeneric = new ResponseGeneric<>(MessagesGeneral.SUCCESS_GET_USER, MessagesGeneral.MESSAGE_GET_USER, responseUserFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	
	@GetMapping(MessagesGeneral.MAPPING_GET_ALL)
	public ResponseEntity<ResponseGeneric<ResponseUserListFinal>> getUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		ResponseUserListFinal responseUserFinal = new ResponseUserListFinal();
		List<ResponseUserData> listResponseUserData = new ArrayList<>();
		ResponseGeneric<ResponseUserListFinal> responseGeneric = null;
		for (EntityUser entityUser : this.userService.getUsers()) {
			ResponseUserData responseUserData = new ResponseUserData();
			responseUserData.setUserId(entityUser.getUserId());
			responseUserData.setName(entityUser.getName());
			responseUserData.setLastname(entityUser.getLastname());
			responseUserData.setUsername(entityUser.getUsername());
			responseUserData.setEmail(entityUser.getEmail());
			responseUserData.setPhone(entityUser.getPhone());
			responseUserData.setPassword(entityUser.getPassword());
			responseUserData.setAge(entityUser.getAge());
			responseUserData.setEnabled(entityUser.getEnabled());
			responseUserData.setCreationAt(entityUser.getCreationAt());
			responseUserData.setModificationAt(entityUser.getModificationAt());
			listResponseUserData.add(responseUserData);
		}
		responseUserFinal.setListResponseUserData(listResponseUserData);
		responseGeneric = new ResponseGeneric<>(
				MessagesGeneral.SUCCESS_GET_ALL_USER, 
				MessagesGeneral.MESSAGE_GET_ALL_USER,
				responseUserFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}
	
	@PatchMapping(MessagesGeneral.MAPPING_PATCH)
	public ResponseEntity<ResponseGeneric<String>> patchUser(@RequestBody UserRequest userRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseGeneric<String> responseGeneric = null;
		this.userService.patchUser(userRequest);
		responseGeneric = new ResponseGeneric<>(
				MessagesGeneral.SUCCESS_PATCH_USER, 
				String.format(MessagesGeneral.MESSAGE_PATCH_USER, userRequest.getUsername()), 
				MessagesGeneral.DATA_PATCH_USER);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	@PutMapping(MessagesGeneral.MAPPING_PUT)
	public ResponseEntity<ResponseGeneric<String>> putUser(@RequestBody UserRequest userRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseGeneric<String> responseGeneric = null;
		this.userService.putUser(userRequest);
		responseGeneric = new ResponseGeneric<>(
				MessagesGeneral.SUCCESS_PUT_USER, 
				String.format(MessagesGeneral.MESSAGE_PUT_USER, userRequest.getUsername()), 
				MessagesGeneral.DATA_PUT_USER);		
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	@DeleteMapping(MessagesGeneral.MAPPING_DELETE)
	public ResponseEntity<ResponseGeneric<String>> deleteUser(@RequestParam("username") String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseGeneric<String> responseGeneric = null;
		this.userService.deleteUser(username);
		responseGeneric = new ResponseGeneric<>(
				MessagesGeneral.SUCCESS_DELETE_USER, 
				String.format(MessagesGeneral.MESSAGE_DELETE_USER, username), 
				MessagesGeneral.DATA_DELETE_USER);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

}