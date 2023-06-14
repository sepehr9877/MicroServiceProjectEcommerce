package com.MicorService.Registration.Entity;


import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class TokenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne()
	@JoinColumn(name="user_id")
	private UsersEntity user;
	@Column
	private String token;

}
