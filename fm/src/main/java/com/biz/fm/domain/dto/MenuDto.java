package com.biz.fm.domain.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.biz.fm.domain.entity.FileData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MenuDto {
	
	@Getter
	@Setter
	public static class MenuResponse{
		private String id;
		private String name;
		private FileData image;
		private String description; 
		private String businessNumber;
		private Integer price;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
		private Timestamp createDate;
	}
	
	@Getter
	@Setter
	public static class MenuUpdate{
		private String name;
		private String description; 
		private Integer price;
	}
	
	@Getter
	@Setter
	public static class MenuImageUpdate{
		@NotBlank(message = "imageId 공백일 수 없습니다")
		@ApiModelProperty(example = "메뉴 이미지 아이디")
		private String imageId;
	}
	
	@Getter
	@Setter
	public static class MenuCreate{
		@JsonProperty(access = Access.READ_ONLY)
		private String id;
		
		@NotBlank
		@ApiModelProperty(example = "메뉴 이름")
		private String name;
		
		@NotBlank
		@ApiModelProperty(example = "메뉴 소개")
		private String description;
		
		@Min(0)
		private Integer price;
		
		@ApiModelProperty(example = "이미지 id")
		private String imageId;
	}
	
	
}
 