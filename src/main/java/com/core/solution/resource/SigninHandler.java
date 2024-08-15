package com.core.solution.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.solution.bussines.UserDetailService;
import com.core.solution.model.payload.SigninRequest;
import com.core.solution.model.response.JwtResponse;
import com.core.solution.model.response.JwtResponseFinal;
import com.core.solution.model.response.ResGeneric;
import com.core.solution.utils.JwtUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class SigninHandler {
	
	@Autowired JwtUtils jwtUtils; 
	@Autowired AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<ResGeneric<JwtResponseFinal>> signin(@RequestBody SigninRequest signinRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		log.info("Inicializa peticion signin con el usuario {} ", signinRequest.getUsername());
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		UserDetailService userDetailService = (UserDetailService) authentication.getPrincipal();
		List<String> roles = userDetailService.getAuthorities().stream().map(item -> item.getAuthority()).toList();		
		JwtResponseFinal jwtResponseFinal = new JwtResponseFinal();	
		jwtResponseFinal.setJwtResponse(
				new JwtResponse( 
						userDetailService.getId(),
						userDetailService.getUsername(), 
						userDetailService.getEmail(), 
						userDetailService.getPhone(), 
						roles,
						this.jwtUtils.generateJwtToken(authentication)));		
		ResGeneric<JwtResponseFinal> responseGeneric = null;		
		responseGeneric = new ResGeneric<>(true, "Signin successfully.", jwtResponseFinal);
		log.info("Genera token de usuario jwt {} ", jwtResponseFinal.getJwtResponse().getToken());
		log.info("Finaliza peticion signin con el usuario {} ", signinRequest.getUsername());
		return new ResponseEntity<>(responseGeneric, headers, HttpStatus.OK);
	}
	
}
