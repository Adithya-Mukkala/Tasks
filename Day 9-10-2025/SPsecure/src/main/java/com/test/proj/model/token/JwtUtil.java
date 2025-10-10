package com.test.proj.model.token;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
 
@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "myverysecretkeymyverysecretkey1234567890abcdef";
	private static final long EXPIRATION = 1000 * 60 *60;
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) 
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact(); 
    }
    
    public String extractUsername(String token) {
    	return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validate(String token) {
    	try {
    		Jwts.parserBuilder() .setSigningKey(key).build().parseClaimsJws(token);
    		return true;
    	}
    	catch(JwtException e) {
    		return false;
    	}
    }
 
}