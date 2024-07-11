package com.goldendust.profile.dao;

public interface MemberDao {
	
	public int isMidPresent(String mid);
	public void insert(String mid, String mpw, String mname, String memail);

}
