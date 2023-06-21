package com.MicorService.EcommerceServer.ProductAOP;

import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Aspect
@Component
public class ProductAOP {

	private  RestTemplate temp=new RestTemplate();
	@Autowired
	private  LoadBalancerClient loadBalancerClient;
	
	
	@Pointcut("@annotation(com.MicorService.EcommerceServer.CustomAnnotation.RequiresAuthorization)")
	public void checkAuthorization() {
		
	}
	
	@Before("checkAuthorization()")
	private void SendingRequestToCheckAuthorization(JoinPoint joinPoint) {
		try {
			Object []args=joinPoint.getArgs();
			String token=(String) args[0];
			ServiceInstance serviceInstance=loadBalancerClient.choose("REGISTERATION");
			String registration_url=serviceInstance.getUri()+"/api/checkrole";
			HttpHeaders headers=new HttpHeaders();
			headers.set("Authorization", token);
			HttpEntity<String> request=new HttpEntity<>(headers);
			ResponseEntity<?>response= this.temp.exchange(registration_url, HttpMethod.GET, request, String.class);
			if(response.getStatusCode()!=HttpStatus.OK) {
				System.out.println("Respoonse");
				throw new IllegalAccessError();
			}
			
			
		
	}catch(Exception ex){
		throw new IllegalAccessError();
	}
	}
}
