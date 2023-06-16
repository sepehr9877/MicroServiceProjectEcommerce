package com.MicorService.Registration.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.stereotype.Component;

import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;

@Aspect
@Component
public class CheckAuthenticationAOP {

	@Autowired
	private final UserRepository userRepository;
	@Autowired 
	private final TokenRespository tokenRespository;
	
	public CheckAuthenticationAOP(UserRepository userRepository,TokenRespository tokenRespository) {
		this.tokenRespository=tokenRespository;
		this.userRepository=userRepository;
	}
	
	
	@Pointcut("@annotation(com.MicorService.Registration.CustomAnnotations.RequiresTokenAuthentication)")
	public void CheckAuthentication() {
		
	}
	@Before("CheckAuthentication()")
	public void CheckingAuthenticationProcess(JoinPoint joinPoint) {
		
		try {
			Object args[]=joinPoint.getArgs();
			String token=(String)args[0];
			
			TokenEntity tokenEntity=this.tokenRespository.findByToken(token);
			
		}catch (Exception ex) {
			throw new MissingRequiredPropertiesException();
		}
		
		
	}
	
	
}
