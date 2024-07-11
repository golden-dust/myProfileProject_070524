package com.goldendust.profile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	
	@GetMapping(value = {"/", "/index"})
	public String home() {
		return "index";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
