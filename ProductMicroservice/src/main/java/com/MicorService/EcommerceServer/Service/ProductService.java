package com.MicorService.EcommerceServer.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MicorService.EcommerceServer.Model.ProductEntity;
import com.MicorService.EcommerceServer.Repository.ProductRepository;
import com.MicorService.EcommerceServer.dto.ProductResponse;
import com.MicorService.EcommerceServer.utils.ImageUtils;

import jakarta.ws.rs.InternalServerErrorException;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	public ProductResponse save_product(String name,MultipartFile file) throws Throwable {
			
			ImageUtils image=new ImageUtils(file);
			String imageurl=image.getFull_url();
			ProductEntity product=new ProductEntity();
			product.setImage_url(imageurl);
			product.setName(name);
			this.productRepository.save(product);
			ProductResponse productResponse=new ProductResponse(product.getId(), product.getName(),imageurl);
			return productResponse;
		
	}
	
}
