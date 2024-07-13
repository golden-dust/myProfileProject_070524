package com.goldendust.profile.dto;

public class CommentDto {
	
	public String cnum;
	public String pnum;
	public String mid;
	public String ctext;
	public String cdate;
	
	public CommentDto() {
		super();
	}

	public CommentDto(String pnum, String mid, String ctext) {
		super();
		this.pnum = pnum;
		this.mid = mid;
		this.ctext = ctext;
	}
	
	public CommentDto(String cnum, String pnum, String mid, String ctext, String cdate) {
		super();
		this.cnum = cnum;
		this.pnum = pnum;
		this.mid = mid;
		this.ctext = ctext;
		this.cdate = cdate;
	}
	
	public String getCnum() {
		return cnum;
	}
	
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	
	public String getPnum() {
		return pnum;
	}
	
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getCtext() {
		return ctext;
	}
	
	public void setCtext(String ctext) {
		this.ctext = ctext;
	}
	
	public String getCdate() {
		return cdate;
	}
	
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}


}
