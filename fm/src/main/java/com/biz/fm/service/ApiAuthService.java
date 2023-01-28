package com.biz.fm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.biz.fm.domain.dto.SignDto.SignIn;
import com.biz.fm.domain.entity.Application;
import com.biz.fm.repository.ApplicationRepository;
import com.biz.fm.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiAuthService {
	
	private final ApplicationRepository applicationRepository;
	private final SignService signService;
	private final MemberRepository memberRepository;
	
	public Map<String, String> requestToken(String appKey) throws NotFoundException, ParseException {
		
		Map<String, String> result = new HashMap<>();
		
		//null 값 처리
		Application app = applicationRepository.findByKey(appKey);
		if(app == null) throw new NotFoundException(null);
		
		//멤버 아이디를 이용해 SignDto.In DTO 로 변환  
		SignIn signInInfo = memberRepository.findById(app.getMemberId()).toMemberSignIn();
		
		//토큰을 만들어 반환
		Map<String, String> createToken = signService.createTokenReturn(signInInfo);
		result.put("accessToken", createToken.get("accessToken"));
		result.put("refreshToken", createToken.get("refreshToken"));
				
		return result;
	}

}
