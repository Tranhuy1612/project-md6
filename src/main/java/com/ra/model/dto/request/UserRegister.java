package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegister {
	@NotEmpty(message = "không được để trống")
	private String fullName;
	@NotEmpty(message = "không được để trống")
	@Pattern(regexp = "^.{6,20}",message = "Tài khoản phải từ 6 - 20 ký tự")
	private String username;
	@NotEmpty(message = "không được để trống")
	@Pattern(regexp = "^.{6,20}",message = "Mật khẩu phải từ 6 - 20 ký tự")
	private String password;
	private Set<String> roles;
}
