package com.goldendust.profile.dao;

import com.goldendust.profile.dto.MemberDto;

public interface MemberDao {
	
	public int isMidPresent(String mid);
	public int insert(String mid, String mpw, String mname, String memail);
	public MemberDto findByMid(String mid);
	public int checkIdAndPw(String mid, String mpw);

}
