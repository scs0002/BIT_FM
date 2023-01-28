package com.biz.fm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.biz.fm.utils.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

//jwt가 유효한 토큰인지 인증하기 위한 필터
//security 설정 시 usernamePasswordAuthenticationFilter 앞에 세팅한다. 
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

	// 생성자 주입
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		// 헤더값을 가져온다.
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		// 유효한 토큰인지 확인
		if (token != null && jwtTokenProvider.validateToken(token)) {
			//토큰이 유효하면 유저 정보를 받아온다.
			Authentication auth = jwtTokenProvider.getAuthentication(token);
			//SecurityContext 에 Authentication 객체를 저장합니다.
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);

	}

}