package com.MicorService.Registration.dto;

import com.MicorService.Registration.Entity.UsersEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class LoginResponse {
	private int id;
	public LoginResponse() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	private String token;
	private String username;
	private String email;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

	public LoginResponse(int id, String token, String username, String email, String password) {
		super();
		this.id = id;
		this.token = token;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
