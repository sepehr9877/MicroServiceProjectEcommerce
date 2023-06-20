package com.MicorService.EcommerceServer.ErrorHandling;

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

}
