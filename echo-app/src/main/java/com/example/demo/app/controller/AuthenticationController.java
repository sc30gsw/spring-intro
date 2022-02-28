package com.example.demo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

	@GetMapping("/login")
	public String viewLoginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String postLogin() {
		return "redirect:/";
	}
}
