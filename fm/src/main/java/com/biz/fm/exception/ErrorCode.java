package com.biz.fm.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	//COMMON
	INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "C001", ""),
	METHOD_NOT_ALLOW(HttpStatus.METHOD_NOT_ALLOWED, "C002", "Method Not Allowed"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Not Found"),
	INSERT_FAIL(HttpStatus.BAD_REQUEST, "C004", "Insert Fail"),
	UPDATE_FAIL(HttpStatus.BAD_REQUEST, "C005", "Update Fail"),
	DELETE_FAIL(HttpStatus.BAD_REQUEST, "C006", "Delete Fail"),
	
	//Member
	EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "M001", "Email is Duplication"),
	DATE_PARSE(HttpStatus.BAD_REQUEST, "M002", "Date format Exception"),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "M003", "Invalid Password"),
	INVALID_EMAIL(HttpStatus.BAD_REQUEST, "M004", "Invalid Email")
	;
	
	private final HttpStatus status;
	private final String code;
	private final String message;
	
	private ErrorCode(final HttpStatus status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
