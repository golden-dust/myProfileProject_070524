package com.goldendust.profile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldendust.profile.utility.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
	
	@GetMapping(value="/join")
	public String toJoin() {
		return "joinForm";
	}
	
	@GetMapping("/mypage")
	public String toMypage(HttpServletRequest request) {
		if (SessionUtil.getSid(request) != null) {
			return "mypage";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	
}
