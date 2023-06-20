package com.MicorService.Registration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicorService.Registration.CustomAnnotations.CheckRoleAnnotation;
import com.MicorService.Registration.CustomAnnotations.RequiresArgsAnnotation;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.Service.RegistrationService;
import com.MicorService.Registration.dto.LoginRequest;
import com.MicorService.Registration.dto.LoginResponse;
import com.MicorService.Registration.dto.RegisterationRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationController {
	
	@Autowired
	private final RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService=registrationService;
	}
	
	
	@PostMapping("/register")
	@CheckRoleAnnotation
	public  ResponseEntity<?> Register(@Valid @RequestBody  UsersEntity registerationRequest){
		
		
		LoginResponse loginResponse= this.registrationService.RegisteringUser(registerationRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@Valid @RequestBody LoginRequest loginRequest){
		LoginResponse loginResponse=this.registrationService.LoginUser(loginRequest);
		return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
	}
}
