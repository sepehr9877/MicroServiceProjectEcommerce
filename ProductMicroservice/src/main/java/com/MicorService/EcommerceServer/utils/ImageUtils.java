package com.MicorService.EcommerceServer.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.zip.Deflater;

import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

public class ImageUtils {
	private String path="C:\\Users\\sepeh\\Documents\\MicroserviceEcommerce\\MicroServiceProjectEcommerce\\ProductMicroservice\\src\\main\\resources\\static\\upload";
	private String filepath;
	private String full_url; 
	public ImageUtils(){
	}
	public void AddFiletoPath(MultipartFile file,String name) throws Throwable {
		
			filepath=Paths.get(path,name+file.getOriginalFilename()).toString();
			File existed_file=new File(filepath);
			if(existed_file.exists()) {
				throw new FileAlreadyExistsException(name+file.getOriginalFilename());
			}
			file.transferTo(new File(filepath));
			String image_url="/static/upload/"+name+file.getOriginalFilename();
			this.full_url="http://localhost:8082"+image_url;
	
	}
	public String getFull_url() {
		return this.full_url;
	}
	public String getFileName(String url) {
		int Lastindex=url.lastIndexOf("/");
		String name=url.substring(Lastindex+1);
		return name;
	}
	public boolean DeleteFromServer(String name) {
		String imagePath=Paths.get(path,name).toString();
		File file=new File(imagePath);
		if(file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

}
