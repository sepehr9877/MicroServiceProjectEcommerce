package com.MicorService.Registration.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;
import com.MicorService.Registration.dto.LoginResponse;

@Service
public class DetailService {

	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final TokenRespository tokenRespository;
	
	
	public DetailService(UserRepository userRepository,TokenRespository tokenRespository) {
		this.tokenRespository=tokenRespository;
		this.userRepository=userRepository;
	}
	
	public  LoginResponse getDetailofUser(String token) {
		try {
			TokenEntity tokenEntity=this.tokenRespository.findByToken(token);
			UsersEntity usersEntity=tokenEntity.getUser();
			LoginResponse loginResponse=new LoginResponse(
					usersEntity.getId(), 
					token,usersEntity.getUsername(),
					usersEntity.getEmail(),
					usersEntity.getPassword());
			return loginResponse;
		}catch(Exception ex) {
			throw new NoSuchElementException();
		}
		
	}
	public LoginResponse AuthorizedUser_getDetailOfUserByEmail(String email) {
		try {
			UsersEntity usersEntity=this.userRepository.findByEmail(email);
			TokenEntity tokenEntity=this.tokenRespository.findByUser(usersEntity);
			LoginResponse loginResponse=new LoginResponse(
					usersEntity.getId(), 
					tokenEntity.getToken(),usersEntity.getUsername(),
					usersEntity.getEmail(),
					usersEntity.getPassword());
			return loginResponse;
		}catch(Exception ex) {
			throw new NoSuchElementException();
		}
	}
	
	
}
