package com.koreait.ex05.domain;

public class Board {

	private String title;
	private String content;
	
	// constructor
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	// getter/setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
