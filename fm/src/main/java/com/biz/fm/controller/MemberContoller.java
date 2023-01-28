package com.biz.fm.controller;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.fm.domain.dto.MemberDto.MemberRead;
import com.biz.fm.domain.dto.MemberDto.MemberUpdate;
import com.biz.fm.domain.entity.Member;
import com.biz.fm.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberContoller {

	private final MemberService memberService;
	
	@GetMapping
	public ResponseEntity<List<MemberRead>> list() throws NotFoundException{
		return ResponseEntity.ok(memberService.getList());
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberRead> get(@PathVariable String memberId) throws NotFoundException{
		return ResponseEntity.ok(memberService.getMemberById(memberId));
	}
	
// Login으로 대체.	
//	@PostMapping
//	public ResponseEntity<?> addMember(@RequestBody Member member){
//		return ResponseEntity.ok(memberService.insertMember(member));
//	}
	
	@PutMapping("/{memberId}")
	public ResponseEntity<MemberRead> update(@PathVariable String memberId, @RequestBody MemberUpdate member){
		return ResponseEntity.ok(memberService.update(memberId, member)); 
	}
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<MemberRead> delete(@PathVariable String memberId){
		return ResponseEntity.ok(memberService.delete(memberId));
	}
	
}
