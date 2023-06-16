package com.MicorService.Registration.ManageError;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collector;

import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.BadGateway;

import jakarta.ws.rs.BadRequestException;



@ControllerAdvice
public class CustomExceptionHandler {
	
	
	private CustomeErrorResponse error=new CustomeErrorResponse();
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
		System.out.println("Enter to this first function");
		Map<String, String>FieldsMessage=new  HashMap<String,String>();
		ex.getFieldErrors().stream()
				.map(fielderror ->FieldsMessage.put(fielderror.getField(), fielderror.getDefaultMessage()) )
				.toList();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FieldsMessage);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex){
		
		this.error.setMessage("No record has found");
		this.error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(this.error);
	}
	@ExceptionHandler(IllegalAccessError.class)
	public ResponseEntity<?>handleIllegalAccessError(IllegalAccessError ex){
		this.error.setStatus(HttpStatus.FORBIDDEN.value());
		this.error.setMessage("this path is for admin ,you are not authorized");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(this.error);
	}
	
	@ExceptionHandler(MissingRequiredPropertiesException.class)
	public ResponseEntity<?> handleMissingRequiredPropertiesException(MissingRequiredPropertiesException ex){
		this.error.setMessage("You are missing token , please put token in header");
		this.error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
	}
	
}
