package com.goldendust.profile.dao;

import java.util.List;

import com.goldendust.profile.dto.BoardDto;

public interface BoardDao {
	
	public void insert(String mid, String mname, String ptitle, String pcontent);
	
	public List<BoardDto> getList(int pageNum, int amount);
	public BoardDto findByPnum(String pnum);
	
	// mapper incomplete
	public int modifyPost(String pnum, String ptitle, String pcontent);
	
	public int deletePost(String pnum);
	
	public int getTotalPostCount();	// 게시판 총 글 수
	
	public int getSearchResultTotalCount(String key);	// 검색어 결과 글 총 개수
	
	// 검색어 결과 리스트 (p.1)
	public List<BoardDto> getSearchResult(int pageNum, int amount, String key);
	
	// 작성자 아이디 가져오기
	public String findMidByPnum(String pnum);
	
}
