package com.biz.fm.controller;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.fm.domain.dto.Sign;
import com.biz.fm.domain.dto.Token;
import com.biz.fm.domain.dto.MemberDto.MemberRead;
import com.biz.fm.domain.entity.Member;
import com.biz.fm.exception.custom.InvalidPasswordException;
import com.biz.fm.service.SignService;
import com.biz.fm.utils.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
	
	private final SignService signService;
	
	@ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(
			@ApiParam(value = "회원가입 정보", required = true) @RequestBody Sign.Up signUpInfo) throws ParseException {

		MemberRead result = signService.signUp(signUpInfo);
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@PostMapping("/signin")
	public ResponseEntity<Token> signin(
			@ApiParam(value = "로그인 정보", required = true) @RequestBody Sign.In signInInfo) {
		return ResponseEntity.ok(signService.signIn(signInInfo));
	}
}
