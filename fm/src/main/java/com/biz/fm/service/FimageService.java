package com.biz.fm.service;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.biz.fm.domain.dto.FimageDto.FimageCreate;
import com.biz.fm.domain.dto.FimageDto.FimageRead;
import com.biz.fm.domain.dto.FimageDto.FimageUpdate;
import com.biz.fm.domain.entity.Fimage;
import com.biz.fm.exception.custom.DeleteFailException;
import com.biz.fm.exception.custom.InsertFailException;
import com.biz.fm.exception.custom.UpdateFailException;
import com.biz.fm.repository.FimageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FimageService {
	
	private final FimageRepository fimageRepository;
	
	
	public List<FimageRead> findAll() throws NotFoundException{

		List<FimageRead> fimageReads = fimageRepository.findAll();
		if(fimageReads.size() == 0) throw new NotFoundException(null);
		return fimageReads;
		
	}
	
	public List<FimageRead> findAllByBusinessNumber(String businessNumber) throws NotFoundException {
		List<FimageRead> fimageReads = fimageRepository.findAllByBusinessNumber(businessNumber);
		if(fimageReads.size() == 0) throw new NotFoundException(null);
		return fimageReads;
	}
	
	public FimageRead findByFimageId(String fimageId) throws NotFoundException {
		FimageRead fimageRead = fimageRepository.findDtoById(fimageId);
		if(fimageRead == null) throw new NotFoundException(null);
		return fimageRead;
	}
	
	public FimageRead insert(FimageCreate fimageCreate) {
		fimageCreate.setId(UUID.randomUUID().toString().replace("-", ""));
		
		int result = fimageRepository.insert(fimageCreate);
		if(result > 0) {
			return fimageRepository.findDtoById(fimageCreate.getId());
		}
		else throw new InsertFailException();
	}
	
	public FimageRead update(String fimageId, FimageUpdate fimage) {
		
		Fimage oldFimage = fimageRepository.findEntityById(fimageId);
		if(oldFimage == null) throw new UpdateFailException();
		
		Fimage newFimage = oldFimage.patch(fimage);
		
		int result = fimageRepository.update(newFimage);
		if(result > 0) {
			return fimageRepository.findDtoById(fimageId);
		}
		else throw new UpdateFailException();
	}
	
	public FimageRead delete(String fimageId) {
		FimageRead fimageRead = fimageRepository.findDtoById(fimageId);
		if(fimageRead == null) throw new DeleteFailException();
		
		int result = fimageRepository.delete(fimageId);
		if(result > 0) {
			return fimageRead;
		}
		else throw new DeleteFailException();
	}

	
	

}
