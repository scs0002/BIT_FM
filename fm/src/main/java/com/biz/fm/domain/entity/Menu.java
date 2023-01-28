package com.biz.fm.domain.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {
	
	private String id;
	private String name;
	private String description; 
	private String imagePath;
	private String businessNumber;
	private Integer price;
	private Timestamp createDate;
	private Timestamp deleteDate;
	
	public Menu patch(Menu requestMenu) {
		
		if(requestMenu.getName() != null) this.setName(requestMenu.getName());
		if(requestMenu.getPrice() != null) this.setPrice(requestMenu.getPrice());
		if(requestMenu.getDescription() != null) this.setDescription(requestMenu.getDescription());
		if(requestMenu.getImagePath() != null) this.setImagePath(requestMenu.getImagePath());
		if(requestMenu.getBusinessNumber() != null) this.setBusinessNumber(requestMenu.getBusinessNumber());
		
		return this;
	}
}
