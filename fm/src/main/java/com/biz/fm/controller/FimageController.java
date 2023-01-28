package com.biz.fm.controller;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.fm.domain.dto.FimageDto.FimageCreate;
import com.biz.fm.domain.dto.FimageDto.FimageRead;
import com.biz.fm.domain.dto.FimageDto.FimageUpdate;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeCreate;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeRead;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeUpdate;
import com.biz.fm.domain.entity.Fimage;
import com.biz.fm.service.FimageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fimage")
@RequiredArgsConstructor
public class FimageController {
	
	private final FimageService fimageService;
	
	@GetMapping
	public ResponseEntity<List<FimageRead>> getList() throws NotFoundException{
		 return ResponseEntity.ok(fimageService.findAll());
	}
	
	@GetMapping("/{fimageId}")
	public ResponseEntity<FimageRead> getByFimageId(@PathVariable String fimageId) throws NotFoundException{
		return ResponseEntity.ok(fimageService.findByFimageId(fimageId));
	}
	
	@PostMapping
	public ResponseEntity<FimageRead> create(@RequestBody FimageCreate fimageCreate){
		return ResponseEntity.ok(fimageService.insert(fimageCreate));
	}
	
	@PutMapping("/{fimageId}")
	public ResponseEntity<FimageRead> update(@PathVariable String fimageId, @RequestBody FimageUpdate fimageUpdate){
		return ResponseEntity.ok(fimageService.update(fimageId, fimageUpdate));
	}
	
	@DeleteMapping("/{fimageId}")
	public ResponseEntity<FimageRead> delete(@PathVariable String fimageId){
		return ResponseEntity.ok(fimageService.delete(fimageId));
	}
	
}
