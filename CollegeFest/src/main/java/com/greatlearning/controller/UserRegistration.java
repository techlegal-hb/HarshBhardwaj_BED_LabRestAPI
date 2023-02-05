package com.greatlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistration {

	private UserService userService;

	public UserRegistration(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public com.greatlearning.data.UserRegistration userRegistrationDto() {
        return new com.greatlearning.data.UserRegistration();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") com.greatlearning.data.UserRegistration registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}