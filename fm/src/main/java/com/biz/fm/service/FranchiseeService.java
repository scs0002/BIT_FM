package com.biz.fm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeCreate;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeResponse;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeUpdate;
import com.biz.fm.domain.dto.FranchiseeDto.Hours;
import com.biz.fm.domain.dto.MenuDto.MenuCreate;
import com.biz.fm.domain.dto.MenuDto.MenuResponse;
import com.biz.fm.domain.entity.Address;
import com.biz.fm.domain.entity.FileData;
import com.biz.fm.domain.entity.Franchisee;
import com.biz.fm.domain.entity.Menu;
import com.biz.fm.exception.custom.DeleteFailException;
import com.biz.fm.exception.custom.InsertFailException;
import com.biz.fm.exception.custom.UpdateFailException;
import com.biz.fm.repository.AddressRepository;
import com.biz.fm.repository.FranchiseeRepository;
import com.biz.fm.repository.FranchiseeimageRepository;
import com.biz.fm.repository.MenuImageRepository;
import com.biz.fm.repository.MenuRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FranchiseeService {
	
	private final ObjectMapper objectMapper;
	private final MenuRepository menuRepository;
	private final AddressRepository addressRepository;
	private final FranchiseeRepository franchiseeRepository;
	private final MenuImageRepository menuImageRepository;
	private final FranchiseeimageRepository franchiseeimageRepository;
	
	public List<FranchiseeResponse> findAllByDistance(Double longitude, Double latitude, Integer radius) throws NotFoundException, JsonMappingException, JsonProcessingException{
		
		List<Franchisee> franchisees = franchiseeRepository.findAllByDistance(longitude, latitude, radius);
		
		if(franchisees.size() == 0) throw new NotFoundException(null);
		else {
			List<FranchiseeResponse> franchiseeResponses = new ArrayList<>();
			for(Franchisee franchisee : franchisees) {
				franchiseeResponses.add(franchisee.toFranchiseeResponse());
			}
			return franchiseeResponses;
		}
	}
	
	public FranchiseeResponse findByBusinessNumber(String businessNumber) throws NotFoundException, JsonMappingException, JsonProcessingException{
		Franchisee franchisee = franchiseeRepository.findByBusinessNumber(businessNumber);
		
		if(franchisee == null) throw new NotFoundException(null);
		
		return franchisee.toFranchiseeResponse();
	}
	
	public List<MenuResponse> findMenuByBusinessNumber(String businessNumber) throws NotFoundException{
		List<MenuResponse> menus = menuRepository.findBybusinessNumber(businessNumber);
		if(menus.size() == 0) throw new NotFoundException(null);

		return menus;
	}
	
	public List<FileData> findAllByBusinessNumber(String businessNumber) throws NotFoundException {
		List<FileData> fimages = franchiseeimageRepository.findAllByBusinessNumber(businessNumber);
		if(fimages.size() == 0) throw new NotFoundException(null);
		
		return fimages;
	}
	
	public Hours findHoursByBusinessNumber(String businessNumber) throws JsonMappingException, JsonProcessingException, NotFoundException {
		String hours =  franchiseeRepository.findHoursByBusinessNumber(businessNumber);
		if(hours == null) throw new NotFoundException(null);
		
		return objectMapper.readValue(franchiseeRepository.findHoursByBusinessNumber(businessNumber), Hours.class);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public FranchiseeResponse insertFranchisee(FranchiseeCreate franchiseeCreate) throws JsonMappingException, JsonProcessingException {
		
		//????????? ?????? ??????.
		Address address = franchiseeCreate.getAddress();
		address.setId(UUID.randomUUID().toString().replace("-", ""));
		addressRepository.insert(address);
		
		// ??????????????? default ????????? ??????.
		if(franchiseeCreate.getFirstImg() == null) franchiseeCreate.setFirstImg("/api/file/a70427302ce343c2bd29054e7dd82cc0-default-image.jpg");
		
		// hours JSON ???????????? ?????? ?????? Serialize.
		String hours = objectMapper.writeValueAsString(franchiseeCreate.getHours());
		
		//????????? ?????? ??????.
		int result = franchiseeRepository.insert(franchiseeCreate, hours);
		if(result > 0) {

			//????????? ?????? ????????? ?????? ??????
			if(franchiseeCreate.getFirstImgId() != null) franchiseeimageRepository.insert(franchiseeCreate.getFirstImgId(), franchiseeCreate.getBusinessNumber());
			else franchiseeimageRepository.insert("a70427302ce343c2bd29054e7dd82cc0", franchiseeCreate.getBusinessNumber());
			return franchiseeRepository.findByBusinessNumber(franchiseeCreate.getBusinessNumber()).toFranchiseeResponse();
		}
		else throw new InsertFailException(); 
	}
	
	public boolean insertFranchiseeImage(String businessNumber, String imageId) {
		//????????? ????????? ?????? ??????. (?????? 10???)
		if(franchiseeimageRepository.franchisesImageCount(businessNumber) >= 10) throw new InsertFailException("????????? ???????????? ?????? 10??? ?????? ???????????????.");
		
		//????????? ????????? ????????? ??????.
		int result = franchiseeimageRepository.insert(imageId, businessNumber);
		if(result > 0) return true;
		else throw new InsertFailException();
	}
	
	public Menu insertMenu(String businessNumber, MenuCreate menuCreate) {
		
		Menu menu = Menu.builder()
				.id(UUID.randomUUID().toString().replace("-", ""))
				.name(menuCreate.getName())
				.description(menuCreate.getDescription())
				.businessNumber(businessNumber)
				.price(menuCreate.getPrice())
				.build();
		
		int result = menuRepository.insert(menu);
		if(result > 0) {
			
			//?????? ????????? ?????? ??????
			if(menuCreate.getImageId() != null) menuImageRepository.insert(menuCreate.getImageId(), menu.getId());
			else menuImageRepository.insert("a70427302ce343c2bd29054e7dd82cc0", menu.getId());
			
			return menuRepository.findById(menu.getId());
		}
		else throw new InsertFailException();
	}
	
	public FranchiseeResponse update(String businessNumber, FranchiseeUpdate franchisee) throws JsonMappingException, JsonProcessingException {
		
		Franchisee oldFranchisee = franchiseeRepository.findByBusinessNumber(businessNumber);
		if(oldFranchisee == null) throw new UpdateFailException("???????????? ???????????? ????????????.");
		
		Franchisee newFranchisee = oldFranchisee.patch(franchisee);
		
		int result = franchiseeRepository.update(newFranchisee);
		if(result > 0) {
			return franchiseeRepository.findByBusinessNumber(businessNumber).toFranchiseeResponse();
		}
		else throw new UpdateFailException();
		
	}
	
	public FranchiseeResponse delete(String businessNumber) throws JsonMappingException, JsonProcessingException {
		
		Franchisee franchisee = franchiseeRepository.findByBusinessNumber(businessNumber);
		if(franchisee == null) throw new DeleteFailException();
		
		int result = franchiseeRepository.delete(businessNumber);
		if(result > 0) {
			return franchisee.toFranchiseeResponse();
		}
		else throw new DeleteFailException();
	}

	public Map<String, Boolean> checkBusinessNumber(String businessNumber) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("result", !franchiseeRepository.checkBusinessNumberExists(businessNumber));
		return map;
	}
}
