package com.core.solution.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.solution.bussines.UserService;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.payload.SignupRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class SignupHandler {
	
	private final UserService userService;
	
	@PostMapping("/signup")
    public ResponseEntity<SignupRequest> signupUser(@RequestBody SignupRequest signupRequest) throws SolutionException {		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);			
		this.userService.signupUser(signupRequest);
		return new ResponseEntity<>(signupRequest, headers, HttpStatus.CREATED);		
	}
	
}
