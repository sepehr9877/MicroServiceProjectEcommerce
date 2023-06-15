package com.MicorService.Registration.AOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.MicorService.Registration.dto.RegisterationRequest;

@Aspect
public class RegistrationAOP {
	
	@Pointcut("@annotation(RequiresTokenAuthentication)")
	public void requiredtokenauthentication() {
		
	}
	@Before("requiredtokenauthentication()")
	public void checkArgsForRegistration( JoinPoint joinpoint) {
		System.out.println("Enter to AOP");
		Object[] args=joinpoint.getArgs();
		boolean reuqired_field=true;
		for(Object arg:args) {
			if (arg instanceof RegisterationRequest){
				String email=((RegisterationRequest) arg).getEmail();
				String password=((RegisterationRequest) arg).getPassword();
				String username=((RegisterationRequest) arg).getUsername();
				if(email==null||password==null||username==null) {
					reuqired_field=false;
				}
			}
			else {
				reuqired_field=false;
			}
		}
		if(reuqired_field==false) {
			throw new IllegalArgumentException("Check the parameter your are sending");
		}
	}
	

}
