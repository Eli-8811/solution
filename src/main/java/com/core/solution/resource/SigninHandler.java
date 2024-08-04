package com.core.solution.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.solution.bussines.UserDetailService;
import com.core.solution.model.payload.SigninRequest;
import com.core.solution.model.response.JwtResponse;
import com.core.solution.model.response.JwtResponseFinal;
import com.core.solution.model.response.ResponseGeneric;
import com.core.solution.utils.JwtUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class SigninHandler {
	
	@Autowired JwtUtils jwtUtils; 
	@Autowired AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<ResponseGeneric<JwtResponseFinal>> getUser(@RequestBody SigninRequest signinRequest) {		
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		UserDetailService userDetailService = (UserDetailService) authentication.getPrincipal();
		List<String> roles = userDetailService.getAuthorities().stream().map(item -> item.getAuthority()).toList();		
		JwtResponseFinal jwtResponseFinal = new JwtResponseFinal();	
		jwtResponseFinal.setJwtResponse(
				new JwtResponse(this.jwtUtils.generateJwtToken(authentication), 
						userDetailService.getId(),
						userDetailService.getUsername(), 
						userDetailService.getEmail(), 
						userDetailService.getPhone(), 
						roles));		
		ResponseGeneric<JwtResponseFinal> responseGeneric = null;		
		responseGeneric = new ResponseGeneric<>(true, "Signin successfully.", jwtResponseFinal);		
		return ResponseEntity.ok(responseGeneric);			
	}
	
}
