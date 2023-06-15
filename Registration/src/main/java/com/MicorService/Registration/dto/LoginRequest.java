package com.MicorService.Registration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull(message = "Email is Null")
	@NotBlank(message ="Emial is Blank")
	private String email;
	@NotNull(message="Password is Null")
	@NotBlank(message="Password is Blank")
	private String password;
	public LoginRequest() {}
	public LoginRequest(String email,String password) {
		this.email=email;
		this.password=password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
