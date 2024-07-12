package com.goldendust.profile.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.goldendust.profile.dao.BoardDao;
import com.goldendust.profile.dao.MemberDao;
import com.goldendust.profile.dto.BoardDto;
import com.goldendust.profile.dto.Criteria;
import com.goldendust.profile.dto.MemberDto;
import com.goldendust.profile.dto.PageDto;
import com.goldendust.profile.utility.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class BoardController {
	
	SqlSession sqlSession;
	
	public BoardController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@GetMapping("/board")
	public String toBoardList(Model model, Criteria criteria, HttpServletRequest request) {
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		
		String selectedPage = request.getParameter("pageNum");	// 클라이언트가 클릭한 게시판 페이지 번호
		
		if (selectedPage != null) {// 게시판 페이지 번호가 선택되지 않은 경우를 제외하고
			// 사용자가 클릭한 페이지 번호를 criteria 객체 내 변수인 pageNum 값으로 세팅
			criteria.setPageNum(Integer.parseInt(selectedPage));
		}
		
		// 게시판 내 모든 글의 총 개수
		int totalPostCount = bDao.getTotalPostCount();
		
		// PageDto 생성
		PageDto pageDto = new PageDto(totalPostCount, criteria);
		
		List<BoardDto> boardList = bDao.getList(criteria.getPageNum(), criteria.getAmount());
		model.addAttribute("bDtos", boardList);
		model.addAttribute("pageDto", pageDto);
		
		return "boardList";
	}
	
	@GetMapping("/board-post{pnum}")
	public String toPost(@PathVariable("pnum") String pnum, HttpServletRequest request, Model model) {
		if (SessionUtil.getSid(request) != null) {
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			BoardDto post = bDao.findByPnum(pnum);
			model.addAttribute("post", post);
			model.addAttribute("mid", SessionUtil.getSid(request));
			return "postView";
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/board-write")
	public String toWrite(HttpServletRequest request, Model model) {
		
		if (SessionUtil.getSid(request) != null) {
			MemberDao mDao = sqlSession.getMapper(MemberDao.class);
			MemberDto mDto = mDao.findByMid(SessionUtil.getSid(request));
			model.addAttribute("mDto", mDto);
			return "writeForm";
		}
		
		return "redirect:login";
	}
	
}
