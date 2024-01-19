package com.ra.security.user_principal;

import com.ra.model.entity.Users;
import com.ra.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users users = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("username not found"));
		return UserPrincipal.build(users);
	}
}
