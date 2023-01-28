package com.biz.fm.domain.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.biz.fm.domain.entity.Address;
import com.biz.fm.validation.HoursValid;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class FranchiseeDto {
	
	@Getter
	@Setter
	@Builder
	public static class FranchiseeResponse{
		private String businessNumber;
		private String name;
		private String firstImg;
		private Address address;
		private Double x;
		private Double y;
		private String tel;
		private String ownerName;
		private String intro;
		private Hours hours;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
		private Timestamp createDate;
	}
	
	@Getter
	@Setter
	public static class FranchiseeCreate{
		
		@NotBlank(message = "businessNumber 공백일 수 없습니다")
		@Size(min = 10, max = 10, message = "사업자 번호는 10 자리여야 합니다.")
		@ApiModelProperty(example = "사업자 번호 ( '-' 제외 )")
		private String businessNumber;
		
		@NotBlank(message = "name 공백일 수 없습니다")
		@ApiModelProperty(example = "가맹점 이름")
		private String name;
		
		@ApiModelProperty(example = "대표이미지 path")
		private String firstImg;
		
		@ApiModelProperty(example = "대표이미지 파일 아이디")
		private String firstImgId;
		
		@NotNull
		private Address address;
		
		@Max(180)
		@Min(-180)
		@NotNull
		private Double x;
		
		@Max(90)
		@Min(-90)
		@NotNull
		private Double y;
		
		@NotBlank(message = "전화번호 공백일 수 없습니다")
		@ApiModelProperty(example = "전화번호")
		private String tel;
		
		@NotBlank(message = "ownerId 공백일 수 없습니다")
		@ApiModelProperty(example = "가맹점주 아이디 ( memberId )")
		private String ownerId;
		
		@NotBlank(message = "intro 공백일 수 없습니다")
		@ApiModelProperty(example = "가맹점 소개 글")
		private String intro;
		
		@NotNull
		@HoursValid
		@ApiModelProperty(example = "가맹점 영업시간 ( JSON -> String )")
		private Hours hours;
		
	}
	
	@Getter
	@Setter
	public static class FranchiseeUpdate{
		@ApiModelProperty(example = "가맹점 이름")
		private String name;
		
		@ApiModelProperty(example = "대표이미지 path")
		private String firstImg;
		
		@ApiModelProperty(example = "전화번호")
		private String tel;
		
		@ApiModelProperty(example = "가맹점 소개 글")
		private String intro;
		
		@HoursValid
		@ApiModelProperty(example = "영업시간 ( JSON -> String )")
		private Hours hours;
	}
	
	@Getter
	@Setter
	public static class Hours {
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String monday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String tuesday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String wednesday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String thursday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String friday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String saturday;
		
		@ApiModelProperty(example = "09:00 ~ 18:00")
		private String sunday;

	}
}
