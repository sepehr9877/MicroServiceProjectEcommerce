package com.MicorService.EcommerceServer.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
			
			ImageUtils image=new ImageUtils();
			image.AddFiletoPath(file,name);
			String imageurl=image.getFull_url();
			ProductEntity product=new ProductEntity();
			product.setImage_url(imageurl);
			product.setName(name);
			this.productRepository.save(product);
			ProductResponse productResponse=new ProductResponse(product.getId(), product.getName(),imageurl);
			return productResponse;
		
	}
	
	public void deleteProduct(String id) {
		
		try {
			ProductEntity product=this.productRepository.findById(Integer.parseInt(id)).get();
			
		
			
			ImageUtils image=new ImageUtils();
			String filename=image.getFileName(product.getImage_url());
			boolean result= image.DeleteFromServer(filename);
			if (result==false) {
				throw new NoSuchElementException();
			}
			this.productRepository.delete(product);
			
		}catch(NoSuchElementException ex) {
			
			throw new NoSuchElementException();
			
		}
	}
	public List<?> getAllProductsDetail() {
		List<ProductEntity> prodcuts=this.productRepository.findAll();
		return prodcuts;
	}
	public ProductResponse getProduct(String id) {
		
		ProductEntity product = productRepository.findById(Integer.parseInt(id))
		        .orElseThrow(InternalServerErrorException::new);
		ProductResponse productResponse = new ProductResponse(product.getId(), product.getName(), product.getImage_url());
	    return productResponse;
	
}
}