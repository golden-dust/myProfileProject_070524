package com.goldendust.profile.dao;

import java.util.List;

import com.goldendust.profile.dto.BoardDto;

public interface BoardDao {
	
	// mapper incomplete
	public int insert(String mid, String mname, String ptitle, String pcontent);
	
	public List<BoardDto> getList(int pageNum, int amount);
	public BoardDto findByPnum(String pnum);
	
	// mapper incomplete
	public int modifyPost(String pnum, String ptitle, String pcontent);
	public int deletePost(String pnum);
	
	public int getTotalPostCount();	// 게시판 총 글 수
	
}
