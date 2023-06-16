package com.MicorService.Registration.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicorService.Registration.Entity.TokenEntity;
import java.util.List;
import com.MicorService.Registration.Entity.UsersEntity;


public interface TokenRespository extends JpaRepository<TokenEntity,Integer> {
	
	public TokenEntity findByUser(UsersEntity user);
	public TokenEntity findByToken(String token);

}
