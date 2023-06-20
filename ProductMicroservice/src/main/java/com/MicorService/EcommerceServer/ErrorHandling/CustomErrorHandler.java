package com.MicorService.EcommerceServer.ErrorHandling;

public class CustomErrorHandler {
	
	private String message;
	private int status;
	public CustomErrorHandler() {
		
	}
	public CustomErrorHandler(String name, int status) {
		super();
		this.message = name;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
