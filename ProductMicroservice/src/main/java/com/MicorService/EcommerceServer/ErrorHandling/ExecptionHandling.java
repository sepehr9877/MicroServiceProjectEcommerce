package com.MicorService.EcommerceServer.ErrorHandling;

import java.nio.file.FileAlreadyExistsException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.ws.rs.InternalServerErrorException;

@ControllerAdvice
public class ExecptionHandling {
	
	private CustomErrorHandler error=new CustomErrorHandler(); 
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<?> handleInternalServer(InternalServerErrorException ex){
		this.error.setMessage("Something went wrong in Db....");
		this.error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.error);
	}
	@ExceptionHandler(IllegalAccessError.class)
	public ResponseEntity<?> handleIllegalAccessError(IllegalAccessError ex){
		this.error.setMessage("You are not authorized to add Product");
		this.error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex){
		this.error.setMessage("No record has been found ,please try with valid value");
		this.error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.error);
	}
	@ExceptionHandler(FileAlreadyExistsException.class)
	public ResponseEntity<?> handleFileAlreadyExistsException(FileAlreadyExistsException ex){
		this.error.setMessage("Please change the name of the image that your are about to upload ,image with this name was already saved");
		this.error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
	}
	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<?> handleClassNotFoundException(ClassNotFoundException ex){
		this.error.setMessage("Please check the detail your are sending ,it seems something is wrong .No record is found with this detail");
		this.error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.error);
	}
	

}
