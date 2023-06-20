package com.MicorService.Registration.ManageError;

import java.sql.SQLIntegrityConstraintViolationException;
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
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.thoughtworks.xstream.converters.javabean.JavaBeanConverter.DuplicateFieldException;
import com.thoughtworks.xstream.converters.javabean.JavaBeanConverter.DuplicatePropertyException;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;



@ControllerAdvice
public class CustomExceptionHandler {
	
	
	private CustomeErrorResponse error=new CustomeErrorResponse();
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
		
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
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleInternalServer(SQLIntegrityConstraintViolationException ex){
		this.error.setMessage("Email already in used");
		this.error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.error);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?>  handleIllegalArgumentException(IllegalArgumentException ex){
		this.error.setMessage("Only USER role can register ,you are passing ADMIN as the role");
		this.error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
	}
	
}
