package com.biz.fm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/")
	public String mainDefault() {
		return "index";
	}
	
	@GetMapping("/index")
	public String main() {
		return "index";
	}
	
	@GetMapping("/menuRegistration")
	public String menuRegistration() {
		return "menuRegistration";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	@GetMapping("/detail")
	public String detail() {
		return "detail";
	}
	@GetMapping("/franchiseeadd")
	public String addfranchisee() {
		return "franchiseeadd";
	}
}
