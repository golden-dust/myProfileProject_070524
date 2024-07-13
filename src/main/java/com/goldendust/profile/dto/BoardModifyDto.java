package com.goldendust.profile.dto;

public class BoardModifyDto {
	
	private String pnum;
	private String ptitle;
	private String pcontent;
	
	public BoardModifyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardModifyDto(String pnum, String ptitle, String pcontent) {
		super();
		this.pnum = pnum;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
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
