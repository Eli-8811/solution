package com.core.solution.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class BaseHandler {

	@ExceptionHandler(value = { SolutionException.class } )
	public ResponseEntity<SolutionData> handleSolutionException(SolutionException exception) {
		log.error(exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getDataException(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { Exception.class, RuntimeException.class } )
	public ResponseEntity<SolutionData> handleSolutionException(Throwable exception) {
		log.error(exception.getMessage(), exception);
		var details = new SolutionData(false, "Ha ocurrido un error en el sistema.", "Ha ocurrido un error inesperado, favor de reportalo con el administrador.", 1000);
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}