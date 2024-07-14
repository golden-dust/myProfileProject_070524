package com.goldendust.profile.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldendust.profile.dao.BoardDao;
import com.goldendust.profile.dao.CommentDao;
import com.goldendust.profile.dao.MemberDao;
import com.goldendust.profile.dto.BoardDto;
import com.goldendust.profile.dto.BoardModifyDto;
import com.goldendust.profile.dto.BoardWriteDto;
import com.goldendust.profile.dto.CommentDto;
import com.goldendust.profile.dto.Criteria;
import com.goldendust.profile.dto.MemberDto;
import com.goldendust.profile.dto.PageDto;
import com.goldendust.profile.service.CommentService;
import com.goldendust.profile.utility.SessionUtil;
import com.goldendust.profile.utility.PostUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
		// 현재 보고 있는 페이지 넘기기
		model.addAttribute("currPage", criteria.getPageNum());
		
		return "boardList";
	}
	
	// 조회수 중복방지 + 조회수 증가
	private void viewCountUp(String pnum, HttpServletRequest req, HttpServletResponse res) {
		
		Cookie oldCookie = null;
		
		// 쿠키에 게시글 조회 목록이 있는지 확인 -> 있으면 oldCookie에 저장
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("postView")) {
					oldCookie = cookie;
				}
			}
		}
		
		if (oldCookie != null) {
			// 쿠키에 클릭한 게시글이 없다면 조회수 업
			if (!oldCookie.getValue().contains("[" + pnum + "]")) {
				BoardDao bDao = sqlSession.getMapper(BoardDao.class);
				bDao.incrementViewCount(pnum);
				oldCookie.setValue(oldCookie.getValue() + "_[" + pnum + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				res.addCookie(oldCookie);
			}
		} else {
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			bDao.incrementViewCount(pnum);
			Cookie newCookie = new Cookie("postView", "[" + pnum + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			res.addCookie(newCookie);
		}
	}
	
	
	@GetMapping("/board-post{pnum}")
	public String toPost(@PathVariable("pnum") String pnum, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (SessionUtil.getSid(request) != null) {
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			
			// 조회수 1 증가 후 게시글 가져오기
			this.viewCountUp(pnum, request, response);
			BoardDto post = bDao.findByPnum(pnum);
			
			// comment list 가져오기
			CommentDao cmDao = sqlSession.getMapper(CommentDao.class);
			List<CommentDto> cmDtos = cmDao.getListByPnum(pnum);
			
			model.addAttribute("post", post);
			model.addAttribute("mid", SessionUtil.getSid(request));
			model.addAttribute("cmDtos", cmDtos);
			return "postView";
		} else {
			return "redirect:login";
		}
	}
	
	@PostMapping("/board-post{pnum}")
	public String addComment(@PathVariable("pnum") String pnum, HttpServletRequest request, Model model) {
		if (SessionUtil.getSid(request) != null) {
			String sid = SessionUtil.getSid(request);
			String ctext = PostUtil.enableEnter(request.getParameter("ctext"));
			CommentService.insertComment(sqlSession, pnum, sid, ctext);

			return "redirect:board-post" + pnum;
		}
		
		return "redirect:login";
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
	
	@PostMapping("/board-write")
	public String writeOk(BoardWriteDto boardWriteDto) {
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		bDao.insert(boardWriteDto.getMid(), 
				boardWriteDto.getMname(), 
				boardWriteDto.getPtitle(), 
				// 오라클 디비에서 \n 자동 삭제로 인한 게시글 개행 불가 수정
				PostUtil.enableEnter(boardWriteDto.getPcontent()));
		return "redirect:board";
	}
	
	
	@GetMapping("/board-search")
	public String searchList(@RequestParam("key") String key, Criteria criteria, Model model) {
BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		
		int totalPostCount = bDao.getSearchResultTotalCount(key);
		
		PageDto pageDto = new PageDto(totalPostCount, criteria);
		
		List<BoardDto> boardList = bDao.getSearchResult(criteria.getPageNum(), criteria.getAmount(), key);

		model.addAttribute("bDtos", boardList);
		model.addAttribute("pageDto", pageDto);
		// 현재 보고 있는 페이지 넘기기
		model.addAttribute("currPage", criteria.getPageNum());
		model.addAttribute("key", key);
		
		return "boardSearchList";
	}

	@GetMapping("/delete-post")
	public String deletePost(@RequestParam("pnum") String pnum, HttpServletRequest request) {
		if (SessionUtil.getSid(request) != null) {
			
			// sid와 게시글 mid 일치 여부 확인
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			String mid = bDao.findMidByPnum(pnum);
			if (mid.equals(SessionUtil.getSid(request))) {
				bDao.deletePost(pnum);
				return "redirect:board";
			} 
			
			request.setAttribute("errorMsg", "본인의 글만 수정할 수 있습니다");
			return "errors/sidMatchError";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/modify-post")
	public String modifyPost(@RequestParam("pnum") String pnum, HttpServletRequest request, Model model) {
		
		if (SessionUtil.getSid(request) != null) {
			
			// sid와 게시글 mid 일치 여부 확인
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			String mid = bDao.findMidByPnum(pnum);
			if (mid.equals(SessionUtil.getSid(request))) {
				BoardDto bDto = bDao.findByPnum(pnum);
				bDto.setPcontent(PostUtil.transToView(bDto.getPcontent()));
				model.addAttribute("post", bDto);
				return "modifyPostForm";
			} 
			
			request.setAttribute("errorMsg", "본인의 글만 수정할 수 있습니다");
			return "errors/sidMatchError";
		}
		
		return "redirect:login";
	}
	
	@PostMapping("/modify-post")
	public String modifyPostOk(BoardModifyDto postToUpdate, HttpServletRequest request, Model model) {
		
		if (SessionUtil.getSid(request) != null) {
			
			// sid와 게시글 mid 일치 여부 확인
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			String mid = bDao.findMidByPnum(postToUpdate.getPnum());
			if (mid.equals(SessionUtil.getSid(request))) {
				bDao.modifyPost(postToUpdate.getPnum(), 
						postToUpdate.getPtitle(), 
						PostUtil.enableEnter(postToUpdate.getPcontent()));
				String url = "redirect:board-post" + postToUpdate.getPnum();
				return url;
			} 
			
			request.setAttribute("errorMsg", "본인의 글만 수정할 수 있습니다");
			return "errors/sidMatchError";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/delete-comment")
	public String deleteComment(@RequestParam("cnum") String cnum, Model model) {
		CommentDao cmDao = sqlSession.getMapper(CommentDao.class);
		
		String pnum = cmDao.getPnum(cnum);
		
		cmDao.delete(cnum);
		
		return "redirect:board-post" + pnum;
	}
	
	@PostMapping("/modify-comment")
	public String modifyComment(HttpServletRequest request, Model model) {
		
		CommentDao cmDao = sqlSession.getMapper(CommentDao.class);
		cmDao.modify(request.getParameter("cnum"), 
				PostUtil.enableEnter(request.getParameter("ctext")));
		
		return "redirect:board-post" + request.getParameter("pnum");
	}

}
