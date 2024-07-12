package com.goldendust.profile.dto;

public class PageDto {
	
	private int startPage;	// 화면에 보여질 하단 페이지 번호 중 시작 페이지 번호
	private int endPage;	// 화면에 보여질 하단 페이지 번호 중 마지막 페이지 번호
	private Boolean next;	// 현재 보여지고 있는 페이지 이상으로 페이지가 더 있는지의 여부
	private Boolean prev;	// 현재 보여지고 있는 페이지 이하로 페이지가 더 있는지의 여부
	private int total;		// 총 글의 개수
	private int lastPage;	// 실제 마지막 페이지
	private Criteria criteria;	// criteria 내의 변수 값들을 불러오기 위한 객체 선언
	
	
	public PageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageDto(int startPage, int endPage, Boolean next, Boolean prev, int total, int lastPage, Criteria criteria) {
		super();
		this.startPage = startPage;
		this.endPage = endPage;
		this.next = next;
		this.prev = prev;
		this.total = total;
		this.lastPage = lastPage;
		this.criteria = criteria;
	}

	public PageDto(int total, Criteria criteria) {
		this.total = total;
		this.criteria = criteria;
		this.endPage = (int) (Math.ceil(criteria.getPageNum() / 10.0) * 10);
		// 정수를 정수로 나누면 무조건 소수점 이하를 버리기 때문에 실수로 나눠주기!
		
		this.startPage = this.endPage - 9;
		
		// ex) 총 128개의 글 존재
		// -> 1 2 3 4 5 6 7 8 9 10 next
		// prev 11 12 13 
		
		this.lastPage = (int) Math.ceil(this.total * 1.0 / criteria.getAmount());
		if (this.endPage > this.lastPage) {
			this.endPage = this.lastPage;
		}
		
		this.prev = this.startPage > 1;	// startPage 값이 1만 아니면 prev가 true로 저장
		this.next = this.endPage < this.lastPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public Boolean getNext() {
		return next;
	}

	public void setNext(Boolean next) {
		this.next = next;
	}

	public Boolean getPrev() {
		return prev;
	}

	public void setPrev(Boolean prev) {
		this.prev = prev;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
}
