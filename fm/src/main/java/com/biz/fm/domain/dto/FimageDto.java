package com.biz.fm.domain.dto;

import java.sql.Timestamp;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

public class FimageDto {
	@Getter
	@Setter
	public static class FimageCreate{
		private String id;
		private String businessNumber;
		private String path;
		private Integer size;
		private String name;
		private String type;
	}
	
	@Getter
	@Setter
	public static class FimageRead{
		private String id;
		private String businessNumber;
		private String path;
		private Integer size;
		private String name;
		private String type;
		private Timestamp createDate;
		
		// domain를 적용하지 않아 접속 마다 IP 달라짐.
		// 접속한 IP 를 추가
		public void setPath(String path) {
			this.path = ServletUriComponentsBuilder.fromCurrentContextPath()
													.path(path)
													.toUriString();
		}
	}
	
	@Getter
	@Setter
	public static class FimageUpdate{
		private String path;
		private Integer size;
		private String name;
		private String type;
	}
}
