package com.biz.fm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biz.fm.filter.*;
import com.biz.fm.utils.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity	//Filter Chain 을 사용한다는 것을 매핑
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//passwordEncoder 을 위한 빈 등록
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

	//spring security 설정이 가능하다.
	//WebSecurity에 접근 혀용 설정을 해버리면 이 설정이 적용되지 않는다. 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic().disable() // rest api 이므로 기본설정 사용안함
				.csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
				.and()
				.authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
				.antMatchers("/api/sign/*").permitAll() // 가입 및 인증 주소는 누구나 접근가능
				.anyRequest().permitAll() // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
				.and()
				//spring security 적용 후에는 모든 리소스에 대한 접근이 제한되므로, swagger 관련 페이지에 대해서는 예외를 적용해야 한다. 
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						UsernamePasswordAuthenticationFilter.class); 
	}

	//spring security 앞단 설정이 가능하다.
	//원하는 url 접근을 허용이 가능하다.(swagger 관련 내용을 접근 가능!) 
	@Override 
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
				"/swagger/**");

	}
}
