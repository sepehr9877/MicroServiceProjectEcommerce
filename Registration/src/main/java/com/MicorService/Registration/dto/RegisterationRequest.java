package com.MicorService.Registration.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterationRequest {
	
	private String username;
	private String password;
	private String email;
	public RegisterationRequest(String username, String password, String email) {
		
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
