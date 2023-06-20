package com.MicorService.EcommerceServer.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.Deflater;

import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

public class ImageUtils {
	private String path="C:\\Users\\sepeh\\Documents\\MicroserviceEcommerce\\MicroServiceProjectEcommerce\\ProductMicroservice\\src\\main\\resources\\static\\upload";
	private String filepath;
	private String full_url; 
	public ImageUtils(MultipartFile file) throws Throwable  {
		
		filepath=Paths.get(path,file.getOriginalFilename()).toString();
		System.out.println(filepath);
		file.transferTo(new File(filepath));
		String image_url="/static/upload/"+file.getOriginalFilename();
		this.full_url="http://localhost:8082"+image_url;
		
		
	}
	public String getFull_url() {
		return this.full_url;
	}

}
