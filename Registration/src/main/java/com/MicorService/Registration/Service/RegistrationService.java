package com.MicorService.Registration.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;
import com.MicorService.Registration.dto.LoginRequest;
import com.MicorService.Registration.dto.LoginResponse;
import com.MicorService.Registration.dto.RegisterationRequest;

@Service
public class RegistrationService {
	
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final TokenService tokenService;
	@Autowired
	private final TokenRespository tokenRespository;
	
	public RegistrationService(UserRepository userRepository,
			TokenService tokenService,
			TokenRespository tokenRespository) {
		this.userRepository=userRepository;
		this.tokenService=tokenService;
		this.tokenRespository=tokenRespository;
		
	}
	public LoginResponse RegisteringUser(UsersEntity user) {
		this.userRepository.save(user);
		TokenEntity token=new TokenEntity();
		String tokenvalue=this.tokenService.generatetoken(user.getEmail());
		token.setToken(tokenvalue);
		token.setUser(user);
		this.tokenRespository.save(token);
		LoginResponse loginResponse=new LoginResponse(token.getId(), token.getToken(), token.getUser());
		return loginResponse;	
	}
	public LoginResponse LoginUser(LoginRequest user) {
		
		try {
			UsersEntity usersEntity= this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
			
			TokenEntity token=this.tokenRespository.findByUser(usersEntity);
			LoginResponse loginResponse=new LoginResponse(usersEntity.getId(),
					token.getToken(),
					usersEntity);
			return loginResponse;
		}catch(Exception ex) {
			throw new NoSuchElementException();
		}
		
	
	
	
	
}
}
