package com.test.proj.model.bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.test.proj.model.filter.JwtFilter;
@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user1 = User.builder().username("user").password(passwordEncoder.encode("password")).roles("USER")
				.build();
		UserDetails user2 = User.builder().username("admin").password(passwordEncoder.encode("admin@123"))
				.roles("ADMIN").build();
		UserDetails user3 = User.builder().username("manager").password(passwordEncoder.encode("manager@123"))
				.roles("MANAGER").build();
		return new InMemoryUserDetailsManager(user1, user2,user3);
	}
	@Bean
	public AuthenticationProvider authProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtFilter) throws Exception {
		return http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() 
                .anyRequest().authenticated()      
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
        .build();
	}
}
