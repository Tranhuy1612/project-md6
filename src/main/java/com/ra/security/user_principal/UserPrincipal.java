package com.ra.security.user_principal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPrincipal implements UserDetails {
	
	private Long id;
	
	private String fullName;
	
	private String userName;
	
	@JsonIgnore
	private String passWord;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetails build(Users users) {
		return UserPrincipal.builder()
				  .id(users.getId())
				  .fullName(users.getFullName())
				  .userName(users.getUserName())
				  .passWord(users.getPassWord())
				  .authorities(users.getRoles().stream().map(item -> new SimpleGrantedAuthority(item.getRoleName().name())).toList())
				  .build();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public String getPassword() {
		return this.passWord;
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
