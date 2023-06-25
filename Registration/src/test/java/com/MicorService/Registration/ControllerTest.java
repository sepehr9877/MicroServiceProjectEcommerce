package com.MicorService.Registration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.MicorService.Registration.Controller.RegistrationController;
import com.MicorService.Registration.Entity.TokenEntity;
import com.MicorService.Registration.Entity.USER_ROLE;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.Service.RegistrationService;
import com.MicorService.Registration.dto.LoginRequest;
import com.MicorService.Registration.dto.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers = RegistrationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ControllerTest {
	@Autowired
	private MockMvc mocMvc;
	@MockBean
	private RegistrationService registrationService;
	
	private UsersEntity usersEntity;
	private TokenEntity tokenEntity;
	private LoginRequest loginRequest;
	private LoginResponse loginResponse;
	@Autowired
	private ObjectMapper objectMapper;
	@BeforeEach
	public void setUp() {
		this.usersEntity=new UsersEntity("sepehr", "sepehr@gmail.com", "sepehr12345", USER_ROLE.USER);
		this.tokenEntity=new TokenEntity(usersEntity.getId(),this.usersEntity,"random_token");
		this.loginRequest=new LoginRequest("sepehr@gmail.com","sepehr12345");

		this.loginResponse = new LoginResponse(this.usersEntity.getId(),this.tokenEntity.getToken(),this.usersEntity.getUsername(),this.usersEntity.getEmail(),this.usersEntity.getPassword());
	}
	
	@Test
	public void testRegistrationReturnedResponse() throws Throwable {
		Mockito.when(this.registrationService.RegisteringUser(Mockito.any(UsersEntity.class))).thenReturn(loginResponse);
		
		ResultActions result=this.mocMvc.perform(post("/api/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.usersEntity)));
		result.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	public void testLoginReturnedResponse()throws Throwable{
		
		Mockito.when(this.registrationService.LoginUser(this.loginRequest)).thenReturn(this.loginResponse);
		ResultActions response=this.mocMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(this.loginRequest)));
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	

}
