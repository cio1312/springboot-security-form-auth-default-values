package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicAssessTestController {

	// in the config class he have not applied security to this pages 
	@GetMapping("/home")
	public String home() {
		
		return "this is home page";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "this is login page";
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "this is register page";
	}
}
