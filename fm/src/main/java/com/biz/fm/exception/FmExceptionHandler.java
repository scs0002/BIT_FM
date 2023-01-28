package com.biz.fm.exception;

import java.text.ParseException;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biz.fm.exception.custom.DeleteFailException;
import com.biz.fm.exception.custom.EmailDuplicationException;
import com.biz.fm.exception.custom.InsertFailException;
import com.biz.fm.exception.custom.InvalidPasswordException;
import com.biz.fm.exception.custom.UpdateFailException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
public class FmExceptionHandler {
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> notFoundException(NotFoundException ex){
		return getResponseEntity(ErrorCode.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailDuplicationException.class)
	public ResponseEntity<?> emailDuplicationException(EmailDuplicationException ex){
		return getResponseEntity(ErrorCode.EMAIL_DUPLICATION);
	}
	
	@ExceptionHandler(value = InvalidPasswordException.class)
	public ResponseEntity<?> invalidPasswordException(InvalidPasswordException ex){
		return getResponseEntity(ErrorCode.INVALID_PASSWORD);
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		return getResponseEntity(ErrorCode.METHOD_NOT_ALLOW);
	}
	
	@ExceptionHandler(value = ParseException.class)
	public ResponseEntity<?> parseException(ParseException ex){
		return getResponseEntity(ErrorCode.DATE_PARSE);
	}
	
	@ExceptionHandler(value = InsertFailException.class)
	public ResponseEntity<?> insertFailException(InsertFailException ex){
		return getResponseEntity(ErrorCode.INSERT_FAIL);
	}
	
	@ExceptionHandler(value = UpdateFailException.class)
	public ResponseEntity<?> updateFailException(UpdateFailException ex){
		return getResponseEntity(ErrorCode.UPDATE_FAIL);
	}
	
	@ExceptionHandler(value = DeleteFailException.class)
	public ResponseEntity<?> deleteFailException(DeleteFailException ex){
		return getResponseEntity(ErrorCode.DELETE_FAIL);
	}
	
	private ResponseEntity<?> getResponseEntity(ErrorCode errorCode){
		ExceptionResponse exceptionResponse = new ExceptionResponse(errorCode);
		return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
	}
}
