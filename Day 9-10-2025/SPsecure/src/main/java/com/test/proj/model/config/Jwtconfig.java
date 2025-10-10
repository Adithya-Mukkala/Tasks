package com.test.proj.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.proj.model.token.JwtUtil;

@Configuration
public class Jwtconfig {

@Bean
public JwtUtil jwtUtil() {
	return new JwtUtil();
}
	
}
