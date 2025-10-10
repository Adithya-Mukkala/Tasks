package com.test.proj.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.proj.model.token.JwtUtil;

import lombok.Data;

@RestController
@RequestMapping("/auth")
public class controller {
 
    @Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private JwtUtil jwtUtil;
 
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(auth.isAuthenticated()) {
        	return jwtUtil.generateToken(authRequest.getUsername());
        }else {
        	throw new RuntimeException("Invalid Credentials");
        }
    }
}
 
@Data
class AuthRequest {
    private String username;
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
