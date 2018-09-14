package com.com.bean;

public class BookVO {
	
	public BookVO() {}
	

	public BookVO(Integer book_id, String book_name) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
	}


	private Integer book_id;
	private String book_name;
	
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	
	
	
}
