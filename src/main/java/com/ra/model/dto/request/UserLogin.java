package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserLogin {
	@NotEmpty(message = "Không được để trống!")
	@Pattern(regexp = "^.{6,20}",message = "Tài khoản phải từ 6 - 20 ký tự")
	private String username;
	@NotEmpty(message = "Không được để trống!")
	@Pattern(regexp = "^.{6,20}",message = "Mật khẩu phải từ 6 - 20 ký tự")
	private String password;
}
