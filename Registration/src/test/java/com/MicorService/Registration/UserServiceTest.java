package com.MicorService.Registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.Entity.USER_ROLE;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.Service.RegistrationService;
import com.MicorService.Registration.Service.TokenService;
import com.MicorService.Registration.UserRepository.TokenRespository;
import com.MicorService.Registration.UserRepository.UserRepository;
import com.MicorService.Registration.dto.LoginResponse;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private TokenRespository tokenRespository;
	
	@Mock
	private TokenEntity tokenEntity;
	
	@Mock
	private TokenService tokenService;

	@InjectMocks
	private RegistrationService registrationService;

	@Test
	public void testRegisterUser() {
		UsersEntity user=new UsersEntity();
		TokenEntity token=new TokenEntity();
		user.setEmail("jack@yahoo.com");
		user.setPassword("jack12345");
		user.setRole(USER_ROLE.USER);
		user.setUsername("Jack");
		when(this.userRepository.save(any(UsersEntity.class))).thenReturn(user);
		when(this.tokenService.generatetoken(user.getEmail())).thenReturn("mocked-token");
		when(this.tokenRespository.save(any(TokenEntity.class))).thenReturn(token);
		
		LoginResponse loginResponse = registrationService.RegisteringUser(user);
		assertEquals(user.getId(), loginResponse.getId());
		assertEquals("mocked-token", loginResponse.getToken());
	}
	
	@Test
	public void testLogin() {
		UsersEntity user=new UsersEntity();
		user.setEmail("jack@yahoo.com");
		user.setPassword("jack12345");
		user.setRole(USER_ROLE.USER);
		user.setUsername("Jack");
		TokenEntity token=new TokenEntity();
		token.setToken("random_token");
		token.setUser(user);
		when(this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
		when(this.tokenRespository.findByUser(user)).thenReturn(token);
		UsersEntity returned_user=this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		TokenEntity returned_token=this.tokenRespository.findByUser(returned_user);
		LoginResponse login=new LoginResponse(returned_user.getId(),returned_token.getToken(),returned_user.getUsername(),returned_user.getEmail(),returned_user.getPassword());
		assertEquals(login.getId(), returned_user.getId());
		assertEquals(login.getToken(), returned_token.getToken());
		
	}
}
