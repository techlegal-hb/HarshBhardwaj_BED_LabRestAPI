package com.greatlearning.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.greatlearning.data.UserRegistration;
import com.greatlearning.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistration registrationDto);
}
