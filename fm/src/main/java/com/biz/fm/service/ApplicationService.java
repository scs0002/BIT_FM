package com.biz.fm.service;

import java.util.UUID;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.biz.fm.domain.dto.ApplicationDto.AppCreate;
import com.biz.fm.domain.dto.ApplicationDto.AppDelete;
import com.biz.fm.domain.dto.ApplicationDto.AppUpdateKey;
import com.biz.fm.domain.dto.ApplicationDto.AppUpdateName;
import com.biz.fm.domain.entity.Application;
import com.biz.fm.domain.entity.Member;
import com.biz.fm.exception.custom.DeleteFailException;
import com.biz.fm.exception.custom.InsertFailException;
import com.biz.fm.exception.custom.UpdateFailException;
import com.biz.fm.repository.ApplicationRepository;
import com.biz.fm.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {
	
	private final ApplicationRepository appRepository;
	private final MemberRepository memberRepository;
	
	public Application getApp(String appId) throws NotFoundException{
		Application app = appRepository.findById(appId);
		if(app == null) throw new NotFoundException(null);
		return app;
	}
	
	public Application insert(AppCreate createAppInfo) {
		
		String email = createAppInfo.getEmail();
		Member member = memberRepository.findByEmail(email);
		
		Application insertApp = Application.builder()
											.id(UUID.randomUUID().toString().replace("-", ""))
											.name(createAppInfo.getName())
											.apiKey(UUID.randomUUID().toString().replace("-", ""))
											.memberId(member.getId())
											.build();
		
		int result = appRepository.insert(insertApp);
		if(result > 0) {
			return appRepository.findById(insertApp.getId());
		}
		else throw new InsertFailException();
	}
	
	public Application nameUpdate(AppUpdateName appName) {

		//수정하고자 하는 앱이 있는지?
		Application newApp = appRepository.findById(appName.getAppId());
		if(newApp == null) throw new UpdateFailException();
		
		Application updateApp = appRepository.findByName(appName.getCurrentName());
		if(updateApp == null) throw new UpdateFailException();
		
		updateApp.setName(appName.getNewName());
		int result = appRepository.nameUpdate(updateApp.toAppUpdate());
		if(result > 0) {
			return appRepository.findById(updateApp.getId());
		}
		else throw new UpdateFailException();
	}
	
	public Application keyUpdate(AppUpdateKey appUpdateKey) {
		//중복검사
		Application checkApp = appRepository.findById(appUpdateKey.getAppId());
		if(checkApp == null) throw new UpdateFailException();
			
		checkApp.setApiKey(UUID.randomUUID().toString().replace("-", ""));
		int result = appRepository.keyUpdate(checkApp.toAppUpdate());
		if(result > 0) {
			return appRepository.findById(checkApp.getId());
		}
		else throw new UpdateFailException();
	}

	public Application delete(AppDelete appDelete) {
		Application deleteApp = appRepository.findById(appDelete.getAppId());
		if(deleteApp == null) throw new DeleteFailException();
		
		int result = appRepository.delete(appDelete.getAppId());
		if(result > 0) {
			return deleteApp;
		}
		else throw new DeleteFailException();
	}

}
