package com.MicorService.EcommerceServer.dto;

public class ProductResponse {
	
	private int id;
	private String name;
	private String image_url;
	public ProductResponse(int id, String name,String image) {
		
		this.id = id;
		this.name = name;
		this.image_url = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image) {
		this.image_url = image;
	}

}
