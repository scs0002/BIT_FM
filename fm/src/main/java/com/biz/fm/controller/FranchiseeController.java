package com.biz.fm.controller;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biz.fm.domain.dto.FimageDto.FimageRead;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeCreate;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeRead;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeUpdate;
import com.biz.fm.service.FimageService;
import com.biz.fm.service.FranchiseeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {
	
	private final FranchiseeService franchiseeService;
	private final FimageService fimageService;
	
	@GetMapping
	public ResponseEntity<List<FranchiseeRead>> getList() throws NotFoundException{
		 return ResponseEntity.ok(franchiseeService.findAll());
	}
	
	@GetMapping("/images")
	public ResponseEntity<List<FimageRead>> getByBusinessNumber(@RequestParam String businessNumber) throws NotFoundException{
		return ResponseEntity.ok(fimageService.findAllByBusinessNumber(businessNumber));
	}
	
	@GetMapping("/{businessNumber}")
	public ResponseEntity<FranchiseeRead> get(@PathVariable String businessNumber) throws NotFoundException{
		return ResponseEntity.ok(franchiseeService.findByBusinessNumber(businessNumber));
	}
	
	@GetMapping("/hours")
	public ResponseEntity<?> getHours(@RequestParam String businessNumber) throws JsonMappingException, JsonProcessingException, NotFoundException{
		return ResponseEntity.ok(franchiseeService.findHoursByBusinessNumber(businessNumber));
	}
	
	@PostMapping
	public ResponseEntity<FranchiseeRead> create(@RequestBody FranchiseeCreate franchiseeCreate){
		return ResponseEntity.ok(franchiseeService.insert(franchiseeCreate));
	}
	
	@PutMapping("/{businessNumber}")
	public ResponseEntity<FranchiseeRead> update(@PathVariable String businessNumber, @RequestBody FranchiseeUpdate franchiseeUpdate){
		return ResponseEntity.ok(franchiseeService.update(businessNumber, franchiseeUpdate));
	}
	
	@DeleteMapping("/{businessNumber}")
	public ResponseEntity<FranchiseeRead> delete(@PathVariable String businessNumber){
		return ResponseEntity.ok(franchiseeService.delete(businessNumber));
	}
}
