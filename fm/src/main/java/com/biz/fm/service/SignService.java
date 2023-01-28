package com.biz.fm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.fm.domain.dto.MemberDto.MemberResponse;
import com.biz.fm.domain.dto.MemberDto.MemberUp;
import com.biz.fm.domain.dto.SignDto.SignIn;
import com.biz.fm.domain.dto.SignDto.SignInfo;
import com.biz.fm.domain.dto.SignDto.SignUp;
import com.biz.fm.domain.dto.UserTokenDto;
import com.biz.fm.domain.entity.Address;
import com.biz.fm.domain.entity.Member;
import com.biz.fm.domain.entity.UserToken;
import com.biz.fm.exception.ErrorCode;
import com.biz.fm.exception.custom.EmailDuplicationException;
import com.biz.fm.exception.custom.InvalidEmailException;
import com.biz.fm.exception.custom.InvalidPasswordException;
import com.biz.fm.exception.custom.Logout;
import com.biz.fm.exception.custom.UnAuthorizationException;
import com.biz.fm.repository.AddressRepository;
import com.biz.fm.repository.MemberRepository;
import com.biz.fm.repository.TokenRepository;
import com.biz.fm.utils.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignService {
	
	private final MemberRepository memberRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberService memberService;
	private final AddressRepository addressRepository;
	
	ErrorCode errorCode;
	
	//회원가입
	public MemberResponse signUp(SignUp signUpinfo) throws ParseException {
		
		boolean result = this.isDuplicate(signUpinfo.getEmail());
		if(result) throw new EmailDuplicationException();
		
		Address address = signUpinfo.getAddress();
		address.setId(UUID.randomUUID().toString().replace("-", ""));
		addressRepository.insert(address);
		
		MemberUp newMember = MemberUp.builder()
								.id(UUID.randomUUID().toString().replace("-", ""))
								.name(signUpinfo.getName())
								.email(signUpinfo.getEmail())
								.password(passwordEncoder.encode(signUpinfo.getPassword()))
								.phoneNumber(signUpinfo.getPhoneNumber())
								.birth(signUpinfo.getBirth())
								.addressId(address.getId())
								.build();

		MemberResponse memberRead =  memberService.insert(newMember);
		
		return memberRead;
	}
	
	
	//로그인
	public SignInfo signIn(SignIn signInInfo) throws ParseException {
		
		//아이디 중복 확인
		Member member = memberRepository.findByEmail(signInInfo.getEmail());
		if(member == null) throw new InvalidEmailException();
		
		//패스워드를 확인.
		if(!passwordEncoder.matches(signInInfo.getPassword(), member.getPassword())) 
			throw new InvalidPasswordException();
		
		//토큰을 만들어 반환
		Map<String, String> createToken = createTokenReturn(signInInfo);
		
		SignInfo signInfo = SignInfo.builder()
							.id(member.getId())
							.name(member.getName())
							.accessToken(createToken.get("accessToken"))
							.refreshToken(createToken.get("refreshToken"))
							.build();
		
		return signInfo;
	}
	
	//로그아웃
	public boolean signOut(String email) {
		int deleteCheck = tokenRepository.delete(email);
		if(deleteCheck>0) return true;
		else return false;
	}
	
	//새로운 토큰 반환
	public Map<String, String> newAccessToken(UserTokenDto.newAccessToken newAccessToken, HttpServletRequest request) throws ParseException{
        
		Map<String,String> result = new HashMap<>();
		UserToken refreshToken = tokenRepository.findByrefreshToken(newAccessToken.getRefreshToken());
        
        //로그아웃의 경우, UnAuthorizationException 예외처리
        if(refreshToken == null) throw new UnAuthorizationException();

        // AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
        if(jwtTokenProvider.validateToken(request, refreshToken.getRefreshToken())){
            String email = jwtTokenProvider.getUserInfo(refreshToken.getRefreshToken());
            SignIn signIn = new SignIn();
            signIn.setEmail(email);

            //엑세스 토큰, 리프레쉬 토큰 모두 발급
            Map<String,String> createToken = createTokenReturn(signIn);
            result.put("accessToken", createToken.get("accessToken"));
            result.put("refreshToken", createToken.get("refreshToken"));
        // RefreshToken 또한 만료된 경우는 로그인을 다시 진행해야 한다.
        }else{
            result.put("code", ErrorCode.ReLogin.getCode());
            result.put("message", ErrorCode.ReLogin.getMessage());
            result.put("HttpStatus", ErrorCode.ReLogin.getStatus().toString());
        }
        return result;
    }
	
	// 토큰을 생성해서 반환
    public Map<String, String> createTokenReturn(SignIn SignInfo) throws ParseException {
    	
        Map<String, String> result = new HashMap<String, String>();

        String accessToken = jwtTokenProvider.createAccessToken(SignInfo).get("accessToken");
        String accessTokenExpirationAt = jwtTokenProvider.createAccessToken(SignInfo).get("accessTokenExpirationAt");
        String refreshToken = jwtTokenProvider.createRefreshToken(SignInfo).get("refreshToken");
        String refreshTokenExpirationAt = jwtTokenProvider.createRefreshToken(SignInfo).get("refreshTokenExpirationAt");
        
        UserToken insertToken = UserToken
						        		.builder()
						                .email(SignInfo.getEmail())
						                .accessToken(accessToken)
						                .accessTokenExpirationAt(accessTokenExpirationAt)
						                .refreshToken(refreshToken)
						                .refreshTokenExpirationAt(refreshTokenExpirationAt)
						                .build();
        
        //토큰 이메일 중복 확인. 재로그인
		if (tokenRepository.findByEmail(insertToken.getEmail()) != null) throw new Logout(); 
        
        //토큰 데이터베이스에 토큰 정보를 입력하고
        tokenRepository.insert(insertToken);

        //액세스 토큰에 대한 정보를 리턴해준다. 
        result.put("accessToken", accessToken);
        result.put("refreshToken", insertToken.getRefreshToken());
        
        return result;
    }
	
	//중복 확인
	public boolean isDuplicate(String email) {
		Member member = memberRepository.findByEmail(email);
		if(member == null) return false;
		else return true;
	}
	
	//패스워드 확인
	public boolean isPassword(SignIn signInInfo) {
		Member beforeMember = memberRepository.findByEmail(signInInfo.getEmail());
		if(beforeMember == null) throw new InvalidEmailException();
		
		boolean checkPassword = passwordEncoder.matches(beforeMember.getPassword(), signInInfo.getPassword());
		
		if(checkPassword) return true;
		else return false;
	}

	
}
