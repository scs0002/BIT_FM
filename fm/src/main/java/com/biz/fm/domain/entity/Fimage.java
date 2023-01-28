package com.biz.fm.domain.entity;

import java.sql.Timestamp;

import com.biz.fm.domain.dto.FimageDto.FimageUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Fimage {
	private String id;
	private String businessNumber;
	private String path;
	private Integer size;
	private String name;
	private String type;
	private Timestamp createDate;
	private Timestamp deleteDate;
	
	public Fimage patch(FimageUpdate newFimage) {
		
		if(newFimage.getPath() != null) this.setPath(newFimage.getPath());
		if(newFimage.getSize() != null) this.setSize(newFimage.getSize());
		if(newFimage.getName() != null) this.setName(newFimage.getName());
		if(newFimage.getType() != null) this.setType(newFimage.getType());
		
		return this;
	}
}
