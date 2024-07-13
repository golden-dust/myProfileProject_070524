package com.goldendust.profile.dao;

import java.util.List;

import com.goldendust.profile.dto.CommentDto;

public interface CommentDao {
	
	public void insert(String pnum, String mid, String ctext);
	public List<CommentDto> getListByPnum(String pnum);
	public void delete(String cnum);
	public String getPnum(String cnum);
	public void modify(String cnum, String ctext);

}
