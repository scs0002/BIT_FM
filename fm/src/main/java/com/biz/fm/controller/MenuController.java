package com.biz.fm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.fm.domain.entity.Menu;
import com.biz.fm.service.MenuService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

	private final MenuService menuService;
	
//	@GetMapping
//	public ResponseEntity<?> list(){
//		return ResponseEntity.ok(menuService.getList());
//	}
	
	@GetMapping("/{businessNumber}")
	public ResponseEntity<?> list(@PathVariable String businessNumber){
		return ResponseEntity.ok(menuService.listByBusinessNumber(businessNumber));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> menu(@PathVariable String id){
		return ResponseEntity.ok(menuService.getMenu(id));
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Menu menu){
		return ResponseEntity.ok(menuService.insertMenu(menu));
	}
	
	@PutMapping("/{menuId}")
	public ResponseEntity<?> update(@PathVariable String menuId, @RequestBody Menu menu){
		return ResponseEntity.ok(menuService.updateMenu(menuId, menu));
	}
	
	@DeleteMapping("/{menuId}")
	public ResponseEntity<?> delete(@PathVariable String menuId){
		return ResponseEntity.ok(menuService.deleteMenu(menuId));
	}
}
