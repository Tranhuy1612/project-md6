package com.ra.service;


import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.request.UserRegister;
import com.ra.model.dto.response.JwtResponse;
import com.ra.model.entity.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {
	
	void register(UserRegister userRegister);
	
	JwtResponse login(UserLogin userLogin);
	
	List<Users> getAllUser();
	
	String handleLogout(Authentication authentication);

	
}
