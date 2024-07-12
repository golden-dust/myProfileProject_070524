package com.goldendust.profile.controller;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldendust.profile.dao.MemberDao;
import com.goldendust.profile.dto.MemberDto;
import com.goldendust.profile.utility.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	SqlSession sqlSession;
	
	public MemberController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@GetMapping(value="/join")
	public String toJoin() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String joinOk(MemberDto mDto, HttpServletRequest request, Model model) {
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int insertFlag = mDao.insert(request.getParameter("mid"), 
				request.getParameter("mpw"), 
				request.getParameter("mname"), 
				request.getParameter("memail"));
		
		if (insertFlag == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("sid", request.getParameter("mid"));
			model.addAttribute("mid", request.getParameter("mid"));
			return "redirect:mypage";
		} else {
			model.addAttribute("errorMsg", "회원 가입 실패");
			return "redirect:join";
		}
	}
	
	@GetMapping("/mypage")
	public String toMypage(HttpServletRequest request, Model model) {
		if (SessionUtil.getSid(request) != null) {
			MemberDao mDao = sqlSession.getMapper(MemberDao.class);
			MemberDto mDto = mDao.findByMid(SessionUtil.getSid(request));
			model.addAttribute("mDto", mDto);
			return "mypage";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		
		String mid = (String) request.getParameter("mid");
		String mpw = (String) request.getParameter("mpw");
		if (mDao.checkIdAndPw(mid, mpw) == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("sid", mid);
			MemberDto mDto = mDao.findByMid(mid);
			model.addAttribute("mDto", mDto);
			return "redirect:mypage";
		}
		
		model.addAttribute("errorMsg", "아이디와 비밀번호를 확인하세요");
		return "redirect:login";
		
	}
	
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		Optional<HttpSession> sessionOptional = Optional.ofNullable(request.getSession(false));
		if (sessionOptional.isPresent()) {
			HttpSession session = sessionOptional.get();
			session.invalidate();
		}
		return "redirect:index";
	}
	
	
}
