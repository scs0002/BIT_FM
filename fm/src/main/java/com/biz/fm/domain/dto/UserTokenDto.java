package com.biz.fm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserTokenDto {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class newAccessToken{
		private String RefreshToken;
	}
	
}
