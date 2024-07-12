package com.goldendust.profile.dto;

public class BoardWriteDto {
	
	private String mid;
	private String mname;
	private String ptitle;
	private String pcontent;
	
	public BoardWriteDto() {
		super();
	}

	public BoardWriteDto(String mid, String mname, String ptitle, String pcontent) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getMname() {
		return mname;
	}
	
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	public String getPtitle() {
		return ptitle;
	}
	
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	
	public String getPcontent() {
		return pcontent;
	}
	
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	
	

}
