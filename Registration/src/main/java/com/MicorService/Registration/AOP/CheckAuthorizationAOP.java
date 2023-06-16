package com.MicorService.Registration.AOP;

import java.util.NoSuchElementException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.Entity.USER_ROLE;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;

import ch.qos.logback.core.subst.Token;
@Aspect
@Component
public class CheckAuthorizationAOP {
	
	@Autowired
	private final TokenRespository tokenRespository;
	@Autowired
	private final UserRepository userRepository;
	
	public CheckAuthorizationAOP(TokenRespository tokenRespository,UserRepository userRepository) {
		this.tokenRespository=tokenRespository;
		this.userRepository=userRepository;
	}
	@Pointcut("@annotation(com.MicorService.Registration.CustomAnnotations.RequiresTokenAuthorization)")
	public void checkingAuthorization() {
		
	}
	
	@Before("checkingAuthorization()")
	public void AuthorizationFunction(JoinPoint joinPoint) {
		try {
			Object args[]= joinPoint.getArgs();
			String token=(String) args[0];
			try {
				TokenEntity tokenEntity= this.tokenRespository.findByToken(token);
				UsersEntity usersEntity=tokenEntity.getUser();
				if(usersEntity.getRole()!=USER_ROLE.ADMIN) {
					throw new IllegalAccessError();
				}
				
			}catch(Exception ex) {
				throw new NoSuchElementException();
			}
		}catch(Exception ex) {
			throw new IllegalAccessError();
		}
		
		
		
	}

}
