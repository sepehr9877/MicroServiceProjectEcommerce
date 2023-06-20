package com.MicorService.EcommerceServer.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MicorService.EcommerceServer.CustomAnnotation.RequiresAuthorization;
import com.MicorService.EcommerceServer.Model.ProductEntity;
import com.MicorService.EcommerceServer.Service.ProductService;
import com.MicorService.EcommerceServer.dto.ProductResponse;
import com.MicorService.EcommerceServer.utils.ImageUtils;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import com.google.common.io.Files;
import com.google.common.net.MediaType;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	@PostMapping
	@RequiresAuthorization
	public ResponseEntity<?> savingProduct(@RequestHeader("Authorization") String token, @RequestParam("file")MultipartFile file,@RequestParam("name")String name ) throws Throwable{
		ProductResponse productResponse= this.productService.save_product(name, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	
	}
	

}
