package com.core.solution.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.core.solution.utils.MessagesResources;

import lombok.SneakyThrows;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserHandler {

	private final UserService userService;

	@SneakyThrows
	@GetMapping(MessagesResources.MAPPING_GET)
	public ResponseEntity<ResponseGeneric<ResponseUserFinal>> getUser(@RequestParam("username") String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseUserFinal responseUserFinal = new ResponseUserFinal();
		ResponseUserData responseUserData = new ResponseUserData();		
		ResponseGeneric<ResponseUserFinal> responseGeneric = null;
		EntityUser entityUser = null;
		entityUser = this.userService.getUser(username.trim());
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
		responseGeneric = new ResponseGeneric<>(
				MessagesResources.SUCCESS, 
				String.format(MessagesResources.MESSAGE_GET_USER, username), 
				responseUserFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	@SneakyThrows
	@GetMapping(MessagesResources.MAPPING_GET_ALL)
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
				MessagesResources.SUCCESS, 
				MessagesResources.MESSAGE_GET_ALL_USER,
				responseUserFinal);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}
	
	@SneakyThrows
	@PatchMapping(MessagesResources.MAPPING_PATCH)
	public ResponseEntity<ResponseGeneric<String>> patchUser(@RequestBody UserRequest userRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseGeneric<String> responseGeneric = null;
		this.userService.patchUser(userRequest);
		responseGeneric = new ResponseGeneric<>(
				MessagesResources.SUCCESS, 
				String.format(MessagesResources.MESSAGE_PATCH_USER, userRequest.getUsername()), 
				MessagesResources.DATA_NULL);
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

	@SneakyThrows
	@PutMapping(MessagesResources.MAPPING_PUT)
	public ResponseEntity<ResponseGeneric<String>> putUser(@RequestBody UserRequest userRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseGeneric<String> responseGeneric = null;
		this.userService.putUser(userRequest);
		responseGeneric = new ResponseGeneric<>(
				MessagesResources.SUCCESS, 
				String.format(MessagesResources.MESSAGE_PUT_USER, userRequest.getUsername()), 
				MessagesResources.DATA_NULL);		
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}

}