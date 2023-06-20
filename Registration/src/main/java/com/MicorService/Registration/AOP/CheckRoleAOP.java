package com.MicorService.Registration.AOP;

import java.util.NoSuchElementException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.MicorService.Registration.Entity.USER_ROLE;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;

@Aspect
@Component
public class CheckRoleAOP {

	@Autowired
	private final TokenRespository tokenRespository;
	@Autowired
	private final UserRepository userRepository;
	
	public CheckRoleAOP(TokenRespository tokenRespository,UserRepository userRepository) {
		this.tokenRespository=tokenRespository;
		this.userRepository=userRepository;
	}
	
	@Pointcut("@annotation(com.MicorService.Registration.CustomAnnotations.CheckRoleAnnotation)")
	public void CheckRole() {
		
	}
	
	@Before("CheckRole()")
	public void CheckRoleProcess(JoinPoint joinPoint) {
		Object obj[]=joinPoint.getArgs();
		
		UsersEntity user=(UsersEntity)obj[0];
		if(user.getRole()==USER_ROLE.ADMIN) {
			
			throw new IllegalArgumentException();
		}
		
		
	}
	
	
}
