package com.MicorService.Registration.Service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	private static final Key Secret_key=Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private static final long EXPIRATION_TIME_MS=86400000;
	
	
	
	public String generatetoken(String email) {
		Date now=new Date();
		Date expire=new Date(now.getTime()+EXPIRATION_TIME_MS);
		return Jwts.builder().setSubject(email)
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(Secret_key)
				.compact();
	}
	
}
