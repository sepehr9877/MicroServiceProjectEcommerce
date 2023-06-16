package com.MicorService.Registration.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MicorService.Registration.Entity.UsersEntity;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
	
	public UsersEntity  findByEmailAndPassword(String email, String password);
	public UsersEntity findByEmail(String email);

}
