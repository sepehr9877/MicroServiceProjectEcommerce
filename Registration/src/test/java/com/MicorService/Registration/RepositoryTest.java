package com.MicorService.Registration;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.MicorService.Registration.Entity.USER_ROLE;
import com.MicorService.Registration.Entity.UsersEntity;
import com.MicorService.Registration.UserRepository.UserRepository;

@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private  UserRepository userRepository;
	private UsersEntity user;
	@BeforeEach
	public void setUp() {
		this.user=new UsersEntity();
		user.setEmail("jack@yahoo.com");
		user.setPassword("jack12345");
		user.setRole(USER_ROLE.USER);
		user.setUsername("jack");
	}
	@Test
	public void UserRepository_save_test() {
		UsersEntity saved_user=this.userRepository.save(user);
		Assertions.assertThat(saved_user).isNotNull();
		Assertions.assertThat(saved_user.getId()).isGreaterThan(0);
	}
	
	@Test
	public void UserRespository_getuser_test() {
		this.userRepository.save(this.user);
		List<UsersEntity> get_users=this.userRepository.findAll();

		Assertions.assertThat(get_users).isNotNull();
		Assertions.assertThat(get_users.size()).isEqualTo(1);
	}
	
	@Test
	public void UserRespository_CustomQuery_test() {
		this.userRepository.save(this.user);
		UsersEntity selected_user=this.userRepository.findByEmailAndPassword("jack@yahoo.com", "jack12345");
		Assertions.assertThat(selected_user).isNotNull();
		Assertions.assertThat(selected_user).isEqualTo(this.user);
		
	}
	
	
	

}
