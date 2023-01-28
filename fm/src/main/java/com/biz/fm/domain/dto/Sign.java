package com.biz.fm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Sign {
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class In {

		private String email;
		private String password;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Up {

		private String name;
		private String email;
		private String password;
		private String phoneNumber;
		private String birth;
		private String gender;
		private String address;
	}

}
