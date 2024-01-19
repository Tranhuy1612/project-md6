package com.ra.service.impl;

import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.request.UserRegister;
import com.ra.model.dto.response.JwtResponse;
import com.ra.model.entity.RoleName;
import com.ra.model.entity.Roles;
import com.ra.model.entity.Users;
import com.ra.repository.IUserRepository;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.jwt.JwtTokenFilter;
import com.ra.security.user_principal.UserDetailService;
import com.ra.security.user_principal.UserPrincipal;
import com.ra.service.IRoleService;
import com.ra.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
	
	@Value("${jwt.expired}")
	private Long EXPIRED;
	
	private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	@Autowired
	private UserDetailService userDetailService;
	
	@Override
	public void register(UserRegister userRegister) {
		if (userRepository.existsByUserName(userRegister.getUsername())) {
			throw new RuntimeException("username is exists");
		}
		Set<Roles> roles = new HashSet<>();
		
		// Nếu không có quyền được truyền lên, mặc định là role user
		if (userRegister.getRoles() == null || userRegister.getRoles().isEmpty()) {
			roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
		} else {
			// Xác định quyền dựa trên danh sách quyền được truyền lên
			userRegister.getRoles().forEach(role -> {
				switch (role) {
					case "ROLE_ADMIN":
						roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
					case "ROLE_USER":
						roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
						break;
					default:
						throw new RuntimeException("role not found");
				}
			});
		}
		userRepository.save(Users.builder()
				  .fullName(userRegister.getFullName())
				  .userName(userRegister.getUsername())
				  .passWord(passwordEncoder.encode(userRegister.getPassword()))
				  .roles(roles)
				  .build());
	}
	
	@Override
	public JwtResponse login(UserLogin userLogin) {
		Authentication authentication;
		try {
			authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
		} catch (AuthenticationException e) {
			throw new RuntimeException("Username or Password is incorrect 11312321");
		}
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		Users users = userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new RuntimeException("user not found"));

		userRepository.save(users);
		// thực hiện trả về cho người dùng
		return JwtResponse.builder()
				  .accessToken(jwtProvider.generateToken(userPrincipal))
				  .expired(EXPIRED)
				  .fullName(userPrincipal.getFullName())
				  .userName(userPrincipal.getUsername())
				  .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
				  .build();
	}
	
	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public String handleLogout(Authentication authentication) {
		return null;
	}

}
