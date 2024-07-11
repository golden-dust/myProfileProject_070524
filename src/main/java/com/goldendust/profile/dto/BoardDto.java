package com.goldendust.profile.dto;

public class BoardDto {
	
	private int pnum;
	private String mid;
	private String mname;
	private String ptitle;
	private String pcontent;
	private String pdate;
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int pnum, String mid, String mname, String ptitle, String pcontent, String pdate) {
		super();
		this.pnum = pnum;
		this.mid = mid;
		this.mname = mname;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pdate = pdate;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
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

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

}
