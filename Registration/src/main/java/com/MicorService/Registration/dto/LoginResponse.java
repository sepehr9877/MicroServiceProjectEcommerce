package com.MicorService.Registration.dto;

import com.MicorService.Registration.Entity.UsersEntity;

public class LoginResponse {
	private int id;
	public LoginResponse() {
		
	}
	public LoginResponse(int id, String token, UsersEntity user) {
		
		this.id = id;
		this.token = token;
		this.user = user;
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
	public UsersEntity getUser() {
		return user;
	}
	public void setUser(UsersEntity user) {
		this.user = user;
	}
	private String token;
	private UsersEntity user;
}
