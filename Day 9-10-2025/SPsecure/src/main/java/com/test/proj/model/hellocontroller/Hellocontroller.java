package com.test.proj.model.hellocontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 @RestController
public class Hellocontroller {
	@GetMapping("/api/user")
	public String UserAccess() {
		return "Hello user! you are authenticated";
		
	}
	
	@GetMapping("/api/admin")
	public String adminAccess() {
		return "Hello Admin! You are authenticated";
	}
	
	@GetMapping("/api/manager")
	public String managerAccess() {
		return "Hello Manager! You are authenticated";
	}
 
}
